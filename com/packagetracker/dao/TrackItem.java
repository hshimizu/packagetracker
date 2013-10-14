package com.packagetracker.dao;

public class TrackItem {
	private String statusTimestamp;
	private String location;
	private String notes;
	
	public String getStatusTimestamp() {
		return statusTimestamp;
	}
	public void setStatusTimestamp(String statusTimestamp) {
		this.statusTimestamp = statusTimestamp;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
}