package com.cks.api.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tamil")
public class MovieApi {
	
	@Autowired
	TamilGunMovieService service;
	
	@RequestMapping(value="/movie", method=RequestMethod.POST,headers = "Accept=application/json")
	public String getMovieUrl(@RequestBody RequestInfo info) {
		return service.getMovieUrl(info.getPlayUrl(),info.getReferredUrl());
	}
	
	@RequestMapping(value="/tracks/{page}")
	public TrackMetadata getTrackInfo(@PathVariable int page) {
		return service.getTrackInfo(page);
	}
	
}
