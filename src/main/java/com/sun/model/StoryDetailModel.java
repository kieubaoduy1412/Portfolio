package com.sun.model;

public class StoryDetailModel {
	private String title;
	private String content;
	private String nextStoryTitle;
	private int nextStoryID;
	private int type;
	private String previousStoryTitle;
	private int previousStoryID;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNextStoryTitle() {
		return nextStoryTitle;
	}
	public void setNextStoryTitle(String nextStoryTitle) {
		this.nextStoryTitle = nextStoryTitle;
	}
	public int getNextStoryID() {
		return nextStoryID;
	}
	public void setNextStoryID(int nextStoryID) {
		this.nextStoryID = nextStoryID;
	}
	public String getPreviousStoryTitle() {
		return previousStoryTitle;
	}
	public void setPreviousStoryTitle(String previousStoryTitle) {
		this.previousStoryTitle = previousStoryTitle;
	}
	public int getPreviousStoryID() {
		return previousStoryID;
	}
	public void setPreviousStoryID(int previousStoryID) {
		this.previousStoryID = previousStoryID;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
