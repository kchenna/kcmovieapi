package com.cks.api.movies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tamil")
public class MovieApi {
	
	@Autowired
	TamilGunMovieService service;
	
	@RequestMapping(value="/movie", method=RequestMethod.POST)
	public String getMovieUrl(@RequestBody RequestInfo info) {
		return service.getMovieUrl(info.getPlayUrl(),info.getReferredUrl());
	}
	
	@RequestMapping("/tracks")
	public List<Track> getTrackInfo(String pageNo) {
		return service.getTrackInfo(pageNo);
	}
	
}
