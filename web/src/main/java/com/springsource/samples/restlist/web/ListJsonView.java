package com.springsource.samples.restlist.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.springframework.web.servlet.view.AbstractView;

import com.springsource.samples.restlist.ItemList;

public class ListJsonView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ItemList itemList = (ItemList) model.get("itemList");
		
		response.setContentType(getContentType());
		
		JsonFactory factory = new JsonFactory();
		JsonGenerator generator = factory.createJsonGenerator(response.getWriter());
		
		generator.writeStartObject();
		
		generator.writeFieldName("name");
		generator.writeString(itemList.getName());
		
		generator.writeFieldName("title");
		generator.writeString(itemList.getTitle());
		
		generator.writeFieldName("items");
		generator.writeStartArray();
		for (String item : itemList.getItems()) {
			generator.writeString(item);
		}
		generator.writeEndArray();
		
		generator.writeEndObject();
		
		generator.flush();
	}
	
	public String getContentType() {
		return "application/json";
	}

}
