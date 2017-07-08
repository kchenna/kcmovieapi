package com.cks.api.movies;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tamil")
public class MovieApi {
	
	@Autowired
	TamilYogiMovieService service;
	
	@RequestMapping("/movies")
    public List<HashMap<String,String>> getProfile(@RequestParam String page) {
		return service.getMovies(page);
	}
}
