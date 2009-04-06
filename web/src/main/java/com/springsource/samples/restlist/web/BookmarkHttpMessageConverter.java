/**
 * 
 */
package com.springsource.samples.restlist.web;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.springsource.samples.restlist.Bookmark;

public final class BookmarkHttpMessageConverter extends AbstractHttpMessageConverter<Bookmark> {

	public BookmarkHttpMessageConverter() {
		super(new MediaType("application", "json"));
	}
	
	@Override
	protected Bookmark readInternal(Class<Bookmark> clazz,
			HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(inputMessage.getBody(), Bookmark.class);
	}

	@Override
	protected void writeInternal(Bookmark t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(outputMessage.getBody(), t);
	}

	public boolean supports(Class<? extends Bookmark> clazz) {
		return Bookmark.class.equals(clazz);
	}
	
}