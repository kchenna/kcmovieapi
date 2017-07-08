package com.cks.api.movies;

import java.util.HashMap;
import java.util.List;

public interface MovieService {
	
	public List<HashMap<String,String>> getMovies(String pageNo);

}
