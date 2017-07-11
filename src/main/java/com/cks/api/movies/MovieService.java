package com.cks.api.movies;

import java.util.List;

public interface MovieService {
	
	public List<Track> getTrackInfo(String pageNo); 
	
	public String getMovieUrl(String playUrl, String referredUrl);

}
