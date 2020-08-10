import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Arrays;

public class UVa10622 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int limit = (int)Math.sqrt(Integer.MAX_VALUE);
		
		while(true) {
			int m = Integer.parseInt(br.readLine());
			int n = Math.abs(m);
			if(n == 0)
				break;
			
			int max = 1;
			for(int i = 2; i <= limit; i++) {
				int x = (int)(Math.log10(n) / Math.log10(i));
				double y = Math.log10(n) / Math.log10(i);
				max = Math.max(max, (x == y) ? (m < 0 ? (x % 2 == 0 ? 0 : x) : x) : 0);
			}
			
			pw.println(m == Integer.MIN_VALUE ? 31 : max);
		}
		
		pw.flush();
		pw.close();
	}
}
