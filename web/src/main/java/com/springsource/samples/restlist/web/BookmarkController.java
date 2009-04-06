package com.springsource.samples.restlist.web;

import java.io.Reader;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springsource.samples.restlist.Bookmark;
import com.springsource.samples.restlist.BookmarkRepository;

@Controller
public class BookmarkController {

	@Autowired
	private BookmarkRepository repository;
	
	@RequestMapping(value="/bookmarks", method=RequestMethod.GET)
	public Collection<Bookmark> bookmarks() {
		return this.repository.findAll();
	}
	
	@RequestMapping(value="/bookmarks", method=RequestMethod.POST)
	public void createBookmark(Reader reader, HttpServletResponse response) throws Exception {
		Bookmark bookmark = readBookmark(reader);
		
		this.repository.createBookmark(bookmark);

		response.setHeader("Location", "/bookmarks/" + bookmark.getKey());
		response.setStatus(201);
		

	}
	private Bookmark readBookmark(Reader reader) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Bookmark bookmark = mapper.readValue(reader, Bookmark.class);
		return bookmark;
	}
	
	@RequestMapping(value="/bookmarks/{key}", method=RequestMethod.GET)
	public ModelAndView findBookmark(@PathVariable String key) throws Exception {
		Bookmark bookmark = this.repository.findBookmark(key);
		return new ModelAndView("bookmark", "bookmark", bookmark);
		
	}
	
	@RequestMapping(value="/bookmarks/{key}", method=RequestMethod.PUT)
	public void updateBookmark(@PathVariable String key, Reader reader, HttpServletResponse response) throws Exception {
		Bookmark bookmark = readBookmark(reader);
		this.repository.updateBookmark(key, bookmark);
		
	}
	
	@RequestMapping(value="/bookmarks/{key}", method=RequestMethod.DELETE)
	public void deleteBookmark(@PathVariable String key) {
		this.repository.deleteBookmark(key);
	}
}
