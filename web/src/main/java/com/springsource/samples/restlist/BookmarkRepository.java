package com.springsource.samples.restlist;

import java.util.Collection;

public interface BookmarkRepository {

	/**
	 * Finds a {@link Bookmark} by its key.
	 * @param key the key of the <code>Bookmark</code>
	 * @return
	 */
	Bookmark findBookmark(String key);
	
	Collection<Bookmark> findAll();
	
	String createBookmark(Bookmark bookmark);
	
	String saveBookmark(Bookmark bookmark);
	
	Collection<Bookmark> searchBookmarks(String term);
}
