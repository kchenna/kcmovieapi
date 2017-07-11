package com.cks.api.movies;

public interface MovieService {
	
	public TrackMetadata getTrackInfo(int page); 
	
	public String getMovieUrl(String playUrl, String referredUrl);

}
