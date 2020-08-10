import java.util.HashMap;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UVa10295{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken()), n = Integer.parseInt(st.nextToken());
		HashMap<String, Integer> map = new HashMap<>();
		while(m-->0) {
			st = new StringTokenizer(br.readLine());
			map.put(st.nextToken(), Integer.parseInt(st.nextToken()));
		}
		
		
		while(n-->0) {
			long salary = 0L;
			
			while(true) {
				String line  = br.readLine();
				if(line.equals("."))
					break;
				
				st = new StringTokenizer(line);
				while(st.hasMoreTokens()) {
					String word = st.nextToken();
					if(map.containsKey(word))
						salary += (long)map.get(word);
				}
			}
			
			pw.println(salary);
		}
		
		pw.flush();
		pw.close();		
	}
}
