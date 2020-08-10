/*import java.util.ArrayList;
import java.util.Collections;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class UVa230{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		ArrayList<Book> shelf = new ArrayList<>();
		
		while(true) {
			String enter = br.readLine();
			if(enter.equals("END"))
				break;
			
			String title = enter.substring(1, enter.lastIndexOf("\""));
			String author = enter.substring(enter.lastIndexOf("by ") + 3, enter.length() - 1);
			
			shelf.add(new Book(title, author));
		}
		
		Collections.sort(shelf);
		
		int[] bookID = new int[shelf.size()];
		
		shelf.contains(Book.title)
		
	}
	
	static class Book implements Comparable<Book> {
		private String title, author;
		
		public Book(String title, String author) {
			this.title = title;
			this.author = author;
		}
		
		public int compareTo(Book b) {
			if(this.author.equals(b.author))
				return this.title.compareTo(b.title);
			return this.author.compareTo(b.author);
		}
	}
}
*/