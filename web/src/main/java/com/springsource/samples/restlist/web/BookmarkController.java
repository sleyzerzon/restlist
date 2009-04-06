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

}
