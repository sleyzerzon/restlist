package com.springsource.samples.restlist.web;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.servlet.view.AbstractView;

import com.springsource.samples.restlist.Bookmark;

public class BookmarksJsonView extends AbstractView {

	@Override
	@SuppressWarnings("unchecked")
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType(getContentType());
		
		Collection<Bookmark> itemList = (Collection<Bookmark>) model.get("bookmarkList");

		ObjectMapper om = new ObjectMapper();
		om.writeValue(response.getWriter(), itemList);
		response.setContentType(getContentType());
		
		response.getWriter().flush();
	}

	public String getContentType() {
		return "application/json";
	}

}
