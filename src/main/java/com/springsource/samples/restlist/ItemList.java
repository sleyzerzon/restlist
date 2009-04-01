package com.springsource.samples.restlist;


public final class ItemList {

	private final String name;
	private final String title;
	private final String[] items;
	
	ItemList(String name, String title, String[] items) {
		this.name = name;
		this.title = title;
		this.items = items;
	}
	
	public String getName() {
		return name;
	}


	public String getTitle() {
		return title;
	}

	public String[] getItems() {
		return items;
	}
	
}
