package com.cks.api.movies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TamilGunMovieService implements MovieService{

	private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36";
	

	private String decodeJS(String encodedJS) {
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



	@Override
	public HashMap getMovies(String pageNo) {
		Document doc;
		List<HashMap<String, String>> movieUrls = new ArrayList<HashMap<String, String>>();

		try {
			String url = "http://tamilgun.ooo/categories/hd-movies/page/"+pageNo+"/";
			doc = Jsoup.connect(url)
					.get();
			
			Elements elements = doc.select("section article");
			Track track = null;
			List<Track> trackList= new ArrayList<Track>(elements.size());

			for (Element element : elements) {
				track = new Track();
				Element title = element.select("section").first().select("h3 a").first();
				Element footer = element.select("footer").first();
				Element views = footer.select("ul li.entry-views").first();
				Element likes = footer.select("ul li.entry-likes a span.touchsize-likes-count").first();
				Element unlikes = footer.select("ul li.entry-likes a span.touchsize-deslikes-count").first();

				track.setPoster(element.select("header img").first().attr("src").trim());
				track.setTitle(title.attr("title"));
				track.setUrl(title.attr("href"));
				track.setNoOfVisits(views.ownText());
				track.setLikes(likes.ownText()); 
				track.setDislikes(unlikes.ownText());
				
				trackList.add(track);
				
			}
			
			Track t = trackList.get(0);
			
			System.out.println(t.getUrl());
			
			doc = Jsoup.connect(t.getUrl())
					.get();
			
			Elements videoContainers = doc.select("div.video-container script");
			//System.out.println(videoContainers.html());
			
			String decodedString = decodeJS(videoContainers.html());

			
			JauntExample example = new JauntExample();
			
			//example.getLinkMap(decodedString);
			
			List<HashMap<String,String>> list = example.getLinkMap(decodedString);
			HashMap<String,String> first = list.get(0);
			
			System.out.println(decodedString);
			
			Connection homeConn = Jsoup.connect(first.get("file")).userAgent(USER_AGENT);
			Document doc1 = homeConn.get();
			Elements container = doc1.select("div[class=video-container]");

			Connection conn = Jsoup.connect(first.get("file")+ "&stream=1").referrer(t.getUrl())
					.userAgent(USER_AGENT);
			conn.ignoreContentType(true).execute();
			
			
			System.out.println(conn.response().url().toString());
			//example.getMovieUrl(first.get("file"));
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMovie(String url) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		new TamilGunMovieService().getMovies("1");
	}

}
