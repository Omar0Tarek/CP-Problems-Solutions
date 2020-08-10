import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;

public class UVa540 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		int x = 1;
		
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0)
				break;
			
			Queue<Integer> main = new LinkedList<>();
			HashMap<Integer, Integer> map = new HashMap<>();
			boolean[] taken = new boolean[n];
			Queue<Integer>[] teams = new Queue[n];
			for(int i = 0; i < n; i++)
				teams[i] = new LinkedList<>();
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				st.nextToken();
				while(st.hasMoreTokens())
					map.put(Integer.parseInt(st.nextToken()), i);
			}
			
			sb.append((x == 1 ? "" : "\n") + "Scenario #" + x++ + "\n");
			
			while(true) {
				st = new StringTokenizer(br.readLine());
				String s = st.nextToken();
				if(s.equals("STOP")) {
					break;
				} else if(s.equals("ENQUEUE")) {
					int elm = Integer.parseInt(st.nextToken());
					int team = map.get(elm);
					teams[team].add(elm);
					if(!taken[team]) {
						main.add(team);
						taken[team] = true;
					}
				} else {
					int team = main.peek();
					sb.append(teams[team].poll() + "\n");
					if(teams[team].size() == 0) {
						main.poll();
						taken[team] = false;
					}
				}
			}
		}
		
		System.out.println(sb);
	}
}
