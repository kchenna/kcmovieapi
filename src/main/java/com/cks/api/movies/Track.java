package com.cks.api.movies;

import java.util.HashMap;
import java.util.List;

public class Track {

	private String url;
	private String title;
	private String noOfVisits;
	private String likes;
	private String dislikes;
	private String baseUrl;
	private String poster;
	private List<HashMap<String, String>> movieLinkMetadata;
	
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public List<HashMap<String, String>> getMovieLinkMetadata() {
		return movieLinkMetadata;
	}
	public void setMovieLinkMetadata(List<HashMap<String, String>> movieLinkMetadata) {
		this.movieLinkMetadata = movieLinkMetadata;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNoOfVisits() {
		return noOfVisits;
	}
	public void setNoOfVisits(String noOfVisits) {
		this.noOfVisits = noOfVisits;
	}
	public String getLikes() {
		return likes;
	}
	public void setLikes(String likes) {
		this.likes = likes;
	}
	public String getDislikes() {
		return dislikes;
	}
	public void setDislikes(String dislikes) {
		this.dislikes = dislikes;
	}
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
}

