import java.util.PriorityQueue;
import java.util.Collections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class UVa11850 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> qu = new PriorityQueue<Integer>(Collections.reverseOrder());
		
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0)
				break;
			
			for(int i = 0; i < n; i++)
				qu.add(Integer.parseInt(br.readLine()));
			
			boolean flag;
			if(1422 - qu.peek() > 100)
				flag = false;
			else
				flag = true;
			
			if(flag == true) {
				while(qu.size() > 1) {
					int check = qu.poll();
					if(check - qu.peek() > 200) {
						flag = false;
						break;
					}
				}
			}
			
			System.out.println(flag ? "POSSIBLE" : "IMPOSSIBLE");
		}
	}
}
