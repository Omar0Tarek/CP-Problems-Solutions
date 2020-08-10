import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

public class UVa574 {
	
	static HashMap<Integer, String> map;
	static int counter;
	
	static int[] array;
	static int want;
	
	public static void backtrack(int i, String s, int c) {
		if(c == want) {
			if(!map.containsValue(s))
				map.put(counter++, s);
			return;
		}
		
		if(i == array.length - 1)
			return;
		
		backtrack(i + 1, s + array[i + 1] + "+", c + array[i + 1]);
		backtrack(i + 1, s, c);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			
			want = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			if(want + n == 0)
				break;
			
			ArrayList<Integer> list = new ArrayList<>();
			while(st.hasMoreTokens())
				list.add(Integer.parseInt(st.nextToken()));
			
			Collections.sort(list);
			Collections.reverse(list);
			
			array = new int[n];
			for(int i = 0; i < list.size(); i++)
				array[i] = list.get(i); 
			
			map = new HashMap<Integer, String>();
			counter = 0;
			backtrack(-1, "", 0);
			
			System.out.printf("Sums of %d:\n", want);
			if(map.size() == 0)
				System.out.println("NONE");
			else
				for(int i = 0; i < counter; i++) {
					String s = map.get(i);
					System.out.println(s.substring(0, s.length() - 1));
				}			
		}
	}
}
