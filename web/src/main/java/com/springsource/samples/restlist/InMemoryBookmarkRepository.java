package com.springsource.samples.restlist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Component;

@Component
final class InMemoryBookmarkRepository implements BookmarkRepository {

	private final ConcurrentMap<String, Bookmark> bookmarks = new ConcurrentHashMap<String, Bookmark>();
	
	public InMemoryBookmarkRepository() {
		createBookmark(new Bookmark("Google", "A search engine", "http://www.google.com"));
		createBookmark(new Bookmark("The Register", "Industry news", "http://www.theregister.co.uk"));
		createBookmark(new Bookmark("Fail Blog", "A fun blog", "http://failblog.org"));
		System.out.println("Creating");
	}
	
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
		bookmark.setKey(key);
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
