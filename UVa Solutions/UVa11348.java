import java.util.TreeMap;
import java.util.ArrayList;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class UVa11348{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		TreeMap<Integer, Integer> tree;
		ArrayList<Integer> array;
		
		int TC = Integer.parseInt(br.readLine());
		for(int currTC = 1; currTC <= TC; currTC++) {
			tree = new TreeMap<>();
			array = new ArrayList<>();
			
			int t = Integer.parseInt(br.readLine());
			for(int i = 1; i <= t; i++) {
				st = new StringTokenizer(br.readLine());
				st.nextToken();
				while(st.hasMoreTokens()) {
					int key = Integer.parseInt(st.nextToken());
					if(tree.containsKey(key) && tree.get(key) != i) {
						array.add(key);
					} else {
						tree.put(key, i);
					}
				}	
			}
			
			for(int i = 0; i < array.size(); i++) {
				int key = array.get(i);
				if(tree.containsKey(key))
					tree.remove(key);
			}
			
			System.out.print("Case " + currTC + ":");
			
			int size = tree.size();
			
			int[] count = new int[t];
			
			while(!tree.isEmpty())
				count[tree.pollFirstEntry().getValue() - 1]++;
			
			for(int i = 0; i < count.length; i++)
				System.out.printf(" %6f" + "%%", size == 0 ? 0 : ((double)count[i] / size) * 100);
			
			System.out.print("\n");
		}
		
	}
}
