package restlist.client;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.springsource.samples.restlist.Bookmark;

public class SimpleClient {

	private static final MediaType JSON_MEDIA_TYPE = new MediaType("application", "json");
	
	private static final String BASE_URI = "http://localhost:8080/restlist";
	
	private static final String BOOKMARKS_URI = BASE_URI + "/bookmarks";
	
	private static final String BOOKMARK_URI = BASE_URI + "/bookmarks/{key}";
	
	private final RestTemplate template = new RestTemplate();
	
	public SimpleClient() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(JSON_MEDIA_TYPE));
		
		this.template.setMessageConverters(new HttpMessageConverter[]{
			new BookmarkHttpMessageConverter(),
			new BookmarkListHttpMessageConverter()
		});
	}
	
	public Bookmark findBookmark(String key) {
		return template.getForObject(BOOKMARK_URI, Bookmark.class, key);
	}
	
	public URI createBookmark(Bookmark bookmark) {
		return template.postForLocation(BOOKMARKS_URI, bookmark);
	}
	
	public void updateBookmark(String key, Bookmark bookmark) {
		template.put(BOOKMARK_URI, bookmark, key);
	}
	
	public void deleteBookmark(String key) {
		template.delete(BOOKMARK_URI, key);
	}
	
	@SuppressWarnings("unchecked")
	public List<Bookmark> listBookmarks() {
		return template.getForObject(BOOKMARKS_URI, List.class);
	}
	
	private static final class BookmarkHttpMessageConverter extends AbstractHttpMessageConverter<Bookmark> {

		public BookmarkHttpMessageConverter() {
			super(JSON_MEDIA_TYPE);
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
	
	@SuppressWarnings("unchecked")
	private static final class BookmarkListHttpMessageConverter extends AbstractHttpMessageConverter<List<Bookmark>> {


		public BookmarkListHttpMessageConverter() {
			super(JSON_MEDIA_TYPE);
		}
		
		@Override
		protected List<Bookmark> readInternal(Class<List<Bookmark>> clazz,
				HttpInputMessage inputMessage) throws IOException,
				HttpMessageNotReadableException {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(inputMessage.getBody(), new TypeReference<List<Bookmark>>(){});
		}

		@Override
		protected void writeInternal(List t, HttpOutputMessage outputMessage)
				throws IOException, HttpMessageNotWritableException {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(outputMessage.getBody(), t);
		}

		public boolean supports(Class<? extends List<Bookmark>> clazz) {
			return List.class.equals(clazz);
		}
		
	}
}
