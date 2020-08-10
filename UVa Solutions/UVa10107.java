import java.util.ArrayList;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class UVa10107{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		ArrayList<Long> array = new ArrayList<>();
		while(br.ready()) {
			st = new StringTokenizer(br.readLine());
			add(array, Long.parseLong(st.nextToken()));
			
			if(array.size() % 2 == 0)
				sb.append((array.get((array.size() - 1) / 2) + array.get((array.size() - 1) / 2 + 1)) / 2);
			else
				sb.append(array.get((array.size() - 1) / 2));
			sb.append("\n");	
		}
		
		System.out.print(sb.toString());
	}
	
	public static void add(ArrayList<Long> array, long numberEnter) {
		for(int i = 0; i < array.size(); i++) {
			if(array.get(i) <= numberEnter) {
				array.add(i, numberEnter);
				return;
			}
		}
		array.add(numberEnter);
	}
}
