package com.cks.api.movies;

import java.util.HashMap;

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
    public HashMap getMovies(@RequestParam String page) {
		System.out.println("REQUEST:");
		return service.getMovies(page);
	}
	
	@RequestMapping("/movie")
    public String getMovie(@RequestParam String url) {
		return service.getMovie(url);
	}
	
}
