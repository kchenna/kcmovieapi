package com.cks.api.movies;

import java.util.HashMap;

public interface MovieService {
	
	public HashMap getMovies(String pageNo);
	
	public String getMovie(String url);

}
