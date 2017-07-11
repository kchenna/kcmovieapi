package com.cks.api.movies;

import java.util.List;

public class TrackMetadata {

	private List<Track> tracks;
	private String nextUrl;
	private boolean hasMoreItems;
	private int count;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<Track> getTracks() {
		return tracks;
	}
	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}
	public String getNextUrl() {
		return nextUrl;
	}
	public void setNextUrl(String nextUrl) {
		this.nextUrl = nextUrl;
	}
	public boolean isHasMoreItems() {
		return hasMoreItems;
	}
	public void setHasMoreItems(boolean hasMoreItems) {
		this.hasMoreItems = hasMoreItems;
	}
	
	
}
