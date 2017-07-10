package com.cks.api.movies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TamilGunMovieService implements MovieService{

	
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
