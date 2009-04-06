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
import com.springsource.samples.restlist.web.BookmarkHttpMessageConverter;

public class SimpleClient {

	static final MediaType JSON_MEDIA_TYPE = new MediaType("application", "json");
	
	private static final String BASE_URI = "http://localhost:8081/restlist";
	
	private static final String BOOKMARKS_URI = BASE_URI + "/bookmarks";
	
	private static final String BOOKMARK_URI = BASE_URI + "/bookmarks/{key}";
	
	private final RestTemplate template = new RestTemplate();
	
	public SimpleClient() {		
		this.template.setMessageConverters(new HttpMessageConverter[]{
			new BookmarkHttpMessageConverter(),
			new BookmarkListHttpMessageConverter()
		});
	}
	
	public Bookmark findBookmark(String key) {
		return null;
	}
	
	public URI createBookmark(Bookmark bookmark) {
		return null;
	}
	
	public void updateBookmark(String key, Bookmark bookmark) {

	}
	
	public void deleteBookmark(String key) {

	}
	
	@SuppressWarnings("unchecked")
	public List<Bookmark> listBookmarks() {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private static final class BookmarkListHttpMessageConverter {
		
	}
}
