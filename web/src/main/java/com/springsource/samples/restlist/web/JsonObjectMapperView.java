package com.springsource.samples.restlist.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.servlet.view.AbstractView;

public class JsonObjectMapperView extends AbstractView {

	private final String modelParameterName;
	
	public JsonObjectMapperView(String modelParameterName) {
		this.modelParameterName = modelParameterName;
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType(getContentType());
		
		Object value = model.get(this.modelParameterName);

		ObjectMapper om = new ObjectMapper();
		om.writeValue(response.getWriter(), value);
		
		response.getWriter().flush();
	}

	public String getContentType() {
		return "application/json";
	}

}
