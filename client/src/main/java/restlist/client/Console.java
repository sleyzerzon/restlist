package restlist.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.springsource.samples.restlist.Bookmark;

public class Console {

	private static final String COMMAND_QUIT = "quit";
	private static final String COMMAND_LIST = "list";
	private static final String COMMAND_FIND = "find";
	private static final String COMMAND_UPDATE = "update";
	private static final String COMMAND_DELETE = "delete";
	private static final Object COMMAND_CREATE = "create";
	
	public static void main(String[] args) throws Exception {
		SimpleClient client = new SimpleClient();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		for(;;) {
			String line = reader.readLine();
			if(line.length() == 0) {
				System.out.println("Invalid command");
				continue;
			}
			
			String[] tokens = line.split(" ");
			
			String command = tokens[0];
			String[] commandArgs = extractArgs(tokens);
			
			if(COMMAND_QUIT.equals(command)) {
				System.out.println("Bye!");
				System.exit(0);
			} else if(COMMAND_LIST.equals(command)) {
				List<Bookmark> bookmarks = client.listBookmarks();
				for (Bookmark bookmark : bookmarks) {
					System.out.println(bookmark.getName() + " <" + bookmark.getKey() + ">" );
				}
			} else if(COMMAND_FIND.equals(command)) {
				String key = commandArgs[0];
				Bookmark bookmark = client.findBookmark(key);
				System.out.println(bookmark.getName());
				System.out.println(bookmark.getDescription());
				System.out.println(bookmark.getUrl());
			} else if(COMMAND_DELETE.equals(command)) {
				String key = commandArgs[0];
				client.deleteBookmark(key);
			} else if(COMMAND_UPDATE.equals(command)) {
				String key = commandArgs[0];
				Bookmark b = new Bookmark();
				b.setName(commandArgs[1]);
				b.setDescription(commandArgs[2]);
				b.setUrl(commandArgs[3]);
				client.updateBookmark(key, b);
			} else if(COMMAND_CREATE.equals(command)) {
				Bookmark b = new Bookmark();
				b.setName(commandArgs[0]);
				b.setDescription(commandArgs[1]);
				b.setUrl(commandArgs[2]);
				client.createBookmark(b);
			}
			
			else {
				System.out.println("Unknown command: " + command);
			}
		}
	}

	private static String[] extractArgs(String[] tokens) {
		String[] args = new String[tokens.length - 1];
		System.arraycopy(tokens, 1, args, 0, args.length);
		return args;
	}
}
