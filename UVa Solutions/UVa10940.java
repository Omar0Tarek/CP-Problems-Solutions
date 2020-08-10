import java.util.Queue;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class UVa10940{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0)
				break;
			
			Queue<Integer> qu = new LinkedList<>();
			for(int i = 2; i <= n; i++)
				if(i % 2 == 0)
					qu.add(i);
			
			if(n % 2 == 1)
				qu.add(qu.poll());
			
			if(n == 1)
				qu.add(1);
			
			while(qu.size() > 1) {
				int size = qu.size();
				
				for(int i = 0; i < Math.ceil(size / 2.0); i++) {
					qu.poll();
					qu.add(qu.poll());
				}
				
			}
			
			System.out.println(qu.poll());
		}
	}
}
/* ANOTHER SOLUTION
import java.util.Queue;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class UVa10940{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0)
				break;
			
			Queue<Integer> qu = new LinkedList<>();
			for(int i = 2; i <= n; i++)
				if(i % 2 == 0)
					qu.add(i);
			
			if(n == 1)
				qu.add(1);
			
			if(n % 2 == 1)
				qu.add(qu.poll());
			
			while(qu.size() > 1) {
				qu.poll();
				qu.add(qu.poll());
			}
			
			System.out.println(qu.poll());
		}
	}
}
*/