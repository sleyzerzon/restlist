package com.springsource.samples.restlist;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class List {

	private final AtomicInteger idGenerator = new AtomicInteger();
	
	private final Map<Integer, ListItem> items = new ConcurrentHashMap<Integer, ListItem>();
	
	private final String name;

	public List(String name) {
		this.name = name;
	}
		
	public String getName() {
		return name;
	}

	public ListItem[] getItems() {
		return (ListItem[]) this.items.values().toArray(new ListItem[this.items.size()]);
	}
	
	public ListItem addListItem(String text) {
		ListItem item = new ListItem(idGenerator.incrementAndGet(), text);
		this.items.put(item.getId(), item);
		return item;
	}
	
	public boolean removeListItem(ListItem listItem) {
		ListItem li = this.items.remove(listItem.getId());
		return li != null;
	}
	
	public boolean updateListItem(ListItem listItem) {
		if(this.items.containsKey(listItem.getId())) {
			this.items.put(listItem.getId(), listItem);
			return true;
		} else {
			return false;
		}
	}
}
