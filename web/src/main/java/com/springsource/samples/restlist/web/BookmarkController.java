package com.springsource.samples.restlist.web;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

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
	public void createBookmark(@RequestBody Bookmark bookmark, HttpServletResponse response) throws Exception {
		this.repository.createBookmark(bookmark);

		response.setHeader("Location", "/bookmarks/" + bookmark.getKey());
		response.setStatus(201);
	}
	
	@RequestMapping(value="/bookmarks/{key}", method=RequestMethod.GET)
	public ModelAndView findBookmark(@PathVariable String key) throws Exception {
		Bookmark bookmark = this.repository.findBookmark(key);
		return new ModelAndView("bookmark", "bookmark", bookmark);
		
	}
	
	@RequestMapping(value="/bookmarks/{key}", method=RequestMethod.PUT)
	public ModelAndView updateBookmark(@PathVariable String key, @RequestBody Bookmark bookmark) throws Exception {
		this.repository.updateBookmark(key, bookmark);
		return new ModelAndView("bookmark", "bookmark", bookmark);
		
	}
	
	@RequestMapping(value="/bookmarks/{key}", method=RequestMethod.DELETE)
	public View deleteBookmark(@PathVariable String key) {
		this.repository.deleteBookmark(key);
		RedirectView view = new RedirectView("/bookmarks", true);
		view.setHttp10Compatible(false);
		return view;
	}
}
