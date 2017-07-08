package com.cks.api.movies;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class TamilYogiMovieService implements MovieService {

	public List<HashMap<String, String>> getMovies(String pageNo) {

		Document doc;
		List<HashMap<String, String>> movieUrls = new ArrayList<HashMap<String, String>>();

		try {
			String url = "http://tamilyogi.cc/category/tamilyogi-bluray-movies/page/" + pageNo + "/";
			doc = Jsoup.connect(url).get();
			Elements elements = doc.select("ul li div a[href]");

			for (Element element : elements) {
				if (!element.hasText()) {
					HashMap<String, String> map = new HashMap<String, String>();
					Elements imageElements = element.select("img");
					map.put("url", element.absUrl("href"));
					map.put("image", imageElements.get(0).attr("src"));
					String title = element.attr("title");
					if (title.indexOf("(") != -1) {
						title = title.substring(0, title.indexOf("(") - 1);
					} else if (title.indexOf("[") != -1) {
						title = title.substring(0, title.indexOf("[") - 1);
					}
					map.put("title", title);
					movieUrls.add(map);
				}
			}

			ExecutorService executor = Executors.newFixedThreadPool(movieUrls.size());
			for (HashMap<String, String> map : movieUrls) {
				if (map.get("url") != null) {
					try {
						Runnable worker = new IFrameRunnable(map);
						executor.execute(worker);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			executor.shutdown();
			while (!executor.isTerminated()) {
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for (HashMap<String, String> movie : movieUrls) {
			if (movie.get("url") != null) {
				list.add(movie);
			}
		}
		
		return list;

	}

	public static class IFrameRunnable implements Runnable {

		private final HashMap<String, String> map;

		IFrameRunnable(HashMap<String, String> map) {
			this.map = map;
		}

		@Override
		public void run() {
			try {
				String u = getIframe(map.get("url"));
				String movieUrl = getMovieUrl(u);
				map.put("url", movieUrl);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private String getIframe(String url) throws IOException {
			Document doc;
			String u = null;
			doc = Jsoup.connect(url).get();
			Elements iframes = doc.select("iframe");
			for (Element iframe : iframes) {
				if (iframe.attr("src") != null) {
					u = iframe.attr("src");
				}
			}
			return u;
		}

		private String getMovieUrl(String iframeUrl) {
			Document doc;
			String url = null;

			try {
				doc = Jsoup.connect(iframeUrl).get();
				Elements scripts = doc.select("script");
				for (Element script : scripts) {
					if (script.html() != null && script.html().indexOf("aboutlink") != -1
							&& script.html().indexOf("http") != -1 && script.html().indexOf("file") != -1) {
						Matcher matcher = Pattern.compile("\\{file:(.*?)\\}")
								.matcher(script.html().replaceAll("(\\s+)", ""));
						if (matcher.find()) {
							String grp = matcher.group();
							String mvurl = grp.substring(7, grp.indexOf(",") - 1);
							url = mvurl;
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			return url;
		}
	}

	public static class DownloadRunnable implements Runnable {

		private final HashMap<String, String> map;
		private String downloadDir ;
		DownloadRunnable(HashMap<String, String> map,String downloadDir) {
			this.map = map;
			this.downloadDir = downloadDir;
		}

		@Override
		public void run() {
			try {
				String url = map.get("url");
				String title = map.get("title");
				String fname = title.replaceAll(" ", "-") + url.substring(url.lastIndexOf("."));
				download( fname, url, downloadDir);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void download(String fileName, String sourceURL, String targetDirectory) throws Exception {
			URL url = new URL(sourceURL);
			Path targetPath = new File(targetDirectory + File.separator + fileName).toPath();
			Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
		}

	}

	
	public static void main(String[] args) throws IOException {
	
		TamilYogiMovieService app = new TamilYogiMovieService();
		List<HashMap<String, String>> movies = app.getMovies("1");
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for (HashMap<String, String> movie : movies) {
			if (movie.get("url") != null) {
				list.add(movie);
			}
		}
		System.out.println("Movie " + list.size());
		System.out.println(list);
		
	}
}
