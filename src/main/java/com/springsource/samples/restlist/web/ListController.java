package com.springsource.samples.restlist.web;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springsource.samples.restlist.ItemList;
import com.springsource.samples.restlist.ListManager;

@Controller
public class ListController {

	private final ListManager mgr = new ListManager();
	
	@RequestMapping(value="/lists", method=RequestMethod.GET)
	public Collection<ItemList> lists() {
		return this.mgr.getAllLists();
	}
	
	
	@RequestMapping(value="/lists/{name}", method=RequestMethod.GET)
	public ModelAndView getList(@PathVariable String name) {
		ItemList list = this.mgr.getList(name);
		if(list == null) {
			throw new IllegalArgumentException();
		}
		ModelAndView mav = new ModelAndView("list");
		mav.addObject(list);
		return mav;
	}
}
