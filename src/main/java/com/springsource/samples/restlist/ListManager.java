package com.springsource.samples.restlist;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ListManager {

	private final ConcurrentMap<String, ItemList> itemLists = new ConcurrentHashMap<String, ItemList>();
	
	public ListManager() {
		createList("Shopping List", new String[]{"apples", "oranges", "strawberries"});
	}
	
	public Collection<ItemList> getAllLists() {
		return this.itemLists.values();
	}
	
	public ItemList getList(String name) {
		return this.itemLists.get(name);
	}
	
	public ItemList createList(String title, String[] items) {
		ItemList itemList = new ItemList(generateBaseName(title), title, items);
		this.itemLists.put(itemList.getName(), itemList);
		return itemList;
	}
	
	private final String generateBaseName(String title) {
		return title.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
	}
	
}
