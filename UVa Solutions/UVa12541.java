import java.util.TreeSet;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class UVa12541{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		TreeSet<Person> tree = new TreeSet<>();
		
		int n = Integer.parseInt(br.readLine());
		while(n-->0) {
			st = new StringTokenizer(br.readLine());
			tree.add(new Person(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		System.out.println(tree.pollLast().name);
		System.out.println(tree.pollFirst().name);
	}
	
	static class Person implements Comparable<Person>{
		private String name;
		private int day, month, year;
		
		public Person(String name, int day, int month, int year) {
			this.name = name;
			this.day = day;
			this.month = month;
			this.year = year;
		}
		
		public int compareTo(Person p) {
			if(this.year == p.year) {
				if(this.month == p.month) {
					if(this.day == p.day) {
						return this.day - p.day;
					}
				}
				return this.month - p.month;
			}
			return this.year - p.year;
		}
	}
}
