import java.util.HashMap;
import java.util.PriorityQueue;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UVa11286 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		HashMap<String, Integer> map;
		PriorityQueue<Integer> qu = new PriorityQueue<>();
		
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0)
				break;
			
			map = new HashMap<>();
			
			int max = 0;
			int repeat = 0;
			
			while(n-->0) {
				st = new StringTokenizer(br.readLine());
				String combination = "";
				for(int i = 0; i < 5; i++)
					qu.add(Integer.parseInt(st.nextToken()));
				combination += qu.poll() + " " + qu.poll() + " " + qu.poll() + " " + qu.poll() + " " + qu.poll();
				
				if(map.containsKey(combination)) {
					map.put(combination, map.get(combination) + 1);
				} else {
					map.put(combination, 1);
				}
				
				int curr = map.get(combination);
				if(curr > max) {
					max = curr;
					repeat = 1;
				} else if(curr == max) {
					repeat++;
				}
			}
			
			pw.println(max * repeat);
		}
		
		pw.flush();
		pw.close();
	}
}
