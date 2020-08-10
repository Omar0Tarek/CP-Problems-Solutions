import java.util.HashSet;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UVa11849 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
			
			if(n + m == 0)
				break;
			
			HashSet<Integer> tree = new HashSet<>();
			int answer = 0;
			for(int i = 1; i <= n; i++)
				tree.add(Integer.parseInt(br.readLine()));
			
			for(int i = 1; i <= m; i++) {
				int in = Integer.parseInt(br.readLine());
				if(tree.contains(in))
					answer++;
			}
			
			pw.println(answer);
		}
		
		pw.flush();
		pw.close();
	}
}
