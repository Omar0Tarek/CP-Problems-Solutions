import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.TreeMap;
import java.util.Map.Entry;


public class UVa12049 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
			
			TreeMap<Integer, Integer> mapN = new TreeMap<>();
			TreeMap<Integer, Integer> mapM = new TreeMap<>();
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				int x = Integer.parseInt(st.nextToken());
				if(!mapN.containsKey(x))
					mapN.put(x, 1);
				else
					mapN.put(x, mapN.get(x) + 1);
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < m; i++) {
				int x = Integer.parseInt(st.nextToken());
				if(!mapM.containsKey(x))
					mapM.put(x, 1);
				else
					mapM.put(x, mapM.get(x) + 1);
			}
			
			int sum = 0;
			while(!mapN.isEmpty()) {
				Entry<Integer, Integer> x = mapN.pollFirstEntry();
				
				if(mapM.containsKey(x.getKey())) {
					sum += Math.abs(x.getValue() - mapM.get(x.getKey()));
					mapM.put(x.getKey(), 0);
				} else {
					sum += x.getValue();
				}
			}
			
			while(!mapM.isEmpty())
				sum += mapM.pollFirstEntry().getValue();
			
			System.out.println(sum);
		}
	}
}
