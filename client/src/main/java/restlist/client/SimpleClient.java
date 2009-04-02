package restlist.client;

import java.io.StringWriter;
import java.net.URI;
import java.util.Arrays;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.springsource.samples.restlist.Bookmark;

public class SimpleClient {

	public static void main(String[] args) throws Exception {
		RestTemplate template = new RestTemplate();
		StringHttpMessageConverter converter = new StringHttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(new MediaType("application", "json")));
		
		template.setMessageConverters(new HttpMessageConverter[]{converter});
		
		String result = template.getForObject("http://localhost:8080/restlist/bookmarks", String.class);
		System.out.println(result);
		
		Bookmark b = new Bookmark("Slashdot", "News for nerds...", "http://www.slashdot.org");
		
		ObjectMapper mapper = new ObjectMapper();
		StringWriter writer = new StringWriter();
		mapper.writeValue(writer, b);
		System.out.println(writer.toString());
		
		URI location = template.postForLocation("http://localhost:8080/restlist/bookmarks",writer.toString());
		System.out.println(location);
	}
	
}
