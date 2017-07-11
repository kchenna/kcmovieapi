package com.cks.api.movies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TamilGunMovieService implements MovieService {

	private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36";

	@Override
	public String getMovieUrl(String playUrl, String referredUrl) {
		try {
			Connection conn = Jsoup.connect(playUrl + "&stream=1").referrer(referredUrl).userAgent(USER_AGENT);
			conn.ignoreContentType(true).execute();
			return conn.response().url().toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public TrackMetadata getTrackInfo(int page) {
		TrackMetadata metadata = new TrackMetadata();
		try {
			String url = "http://tamilgun.ooo/categories/hd-movies/page/" + page + "/";
			Document doc = Jsoup.connect(url).userAgent(USER_AGENT).get();
			Elements elements = doc.select("section article");
			Track track = null;
			List<Track> trackList = new ArrayList<Track>(elements.size());

			for (Element element : elements) {
				track = new Track();
				Element title = element.select("section").first().select("h3 a").first();
				Element footer = element.select("footer").first();
				Element views = footer.select("ul li.entry-views").first();
				Element likes = footer.select("ul li.entry-likes a span.touchsize-likes-count").first();
				Element unlikes = footer.select("ul li.entry-likes a span.touchsize-deslikes-count").first();

				track.setPoster(element.select("header img").first().attr("src").trim());
				track.setTitle(title.attr("title"));
				track.setBaseUrl(title.attr("href"));
				track.setNoOfVisits(views.ownText());
				track.setLikes(likes.ownText());
				track.setDislikes(unlikes.ownText());

				trackList.add(track);

			}

			ExecutorService executor = Executors.newFixedThreadPool(trackList.size());
			for (Track trackObj : trackList) {
				try {
					Runnable worker = new VideoContainerRunnable(trackObj);
					executor.execute(worker);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			metadata.setTracks(trackList);
			metadata.setHasMoreItems(true);
			metadata.setNextUrl("http://tamilgun.ooo/categories/hd-movies/page/"+(page+1)+"/");
			metadata.setCount(trackList.size());
			
			executor.shutdown();
			while (!executor.isTerminated()) {
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return metadata;
	}

	public static class VideoContainerRunnable implements Runnable {

		private final Track track;

		VideoContainerRunnable(Track track) {
			this.track = track;
		}

		@Override
		public void run() {
			try {
				Document doc = Jsoup.connect(track.getBaseUrl()).get();
				Elements videoContainers = doc.select("div.video-container script");
				String decodedString = decodeJS(videoContainers.html());
				track.setMovieLinkMetadata(getMovieLinkMetadata(decodedString));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private String decodeJS(String encodedJS) {
			System.out.println("encodedJS "+encodedJS);
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("nashorn");
			try {
				String evalFn = encodedJS.substring("eval(".length(), encodedJS.lastIndexOf(")"));
				String evaluate = "var decode =" + evalFn;
				engine.eval(evaluate);
				String decoded = (String) engine.eval("decode");
				return decoded;
			} catch (ScriptException e) {
				System.err.println("e.getMessage():" + e.getMessage());
			}
			return null;
		}

		private List<HashMap<String, String>> getMovieLinkMetadata(String decodedString) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				String res = decodedString.substring(decodedString.indexOf("sources") + 8,
						decodedString.lastIndexOf("]});") + 1);
				return mapper.readValue(res, new TypeReference<List<HashMap<String, String>>>() {
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}

}
