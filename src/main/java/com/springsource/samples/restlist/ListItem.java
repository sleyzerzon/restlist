package com.springsource.samples.restlist;

public class ListItem {

	private final int id;
	private final String text;
	private volatile boolean complete = false;

	ListItem(int id, String text) {
		this.id = id;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public boolean isComplete() {
		return complete;
	}

	public void markComplete() {
		this.complete = true;
	}
}
