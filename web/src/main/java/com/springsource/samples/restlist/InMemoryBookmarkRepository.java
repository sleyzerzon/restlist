package com.springsource.samples.restlist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryBookmarkRepository implements BookmarkRepository {

	final ConcurrentMap<String, Bookmark> bookmarks = new ConcurrentHashMap<String, Bookmark>();
	
	public Bookmark findBookmark(String key) {
		return this.bookmarks.get(key);
	}
	
	

	public Collection<Bookmark> findAll() {
		List<Bookmark> l = new ArrayList<Bookmark>(this.bookmarks.values());
		Collections.sort(l);
		return l;
	}



	public String createBookmark(Bookmark bookmark) {
		String key = generateKey(bookmark);
		if(this.bookmarks.putIfAbsent(key, bookmark) == null)  {
			bookmark.setKey(key);
			System.out.println(key);
			return key;
		} else {
			throw new IllegalArgumentException();
		}
	}



	public String updateBookmark(String key, Bookmark bookmark) {
		Bookmark oldValue = this.bookmarks.replace(key, bookmark);
		if(oldValue == null) {
			throw new IllegalArgumentException();
		}
		return key;
	}
	
	public void deleteBookmark(String key) {
		this.bookmarks.remove(key);
	}

	public Collection<Bookmark> searchBookmarks(String term) {
		// TODO Auto-generated method stub
		return null;
	}

	private final String generateKey(Bookmark bookmark) {
		String baseKey = bookmark.getName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
		return baseKey;
	}
}
