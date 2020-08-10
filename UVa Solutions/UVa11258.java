import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class UVa11258 {
	
	static int n, INF = (int)1e9;
	static long[][] memo;
	static String s;
	
	public static long dp(int i, int j) {
		if(i == n)
			return 0;
		if(j == n)
			return sum(i, j - 1);
		
		if(memo[i][j] != -1)
			return memo[i][j];
		
		if(1l * sum(i, j) > 1l * Integer.MAX_VALUE)
			return -INF;
		
		return memo[i][j] = Math.max(sum(i, j) + dp(j + 1, j + 1), dp(i, j + 1));
	}
	
	public static long sum(int i, int j) {
		String x = s.substring(i, j + 1);
		if(x.equals("0"))
			return 0;
		
		if(s.charAt(i) == '0')
			return -INF;
		
		long n = Long.parseLong(x);
		return n;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			s = br.readLine();
			n = s.length();
			memo = new long[n][n];
			for(int i = 0; i < n; i++)
				Arrays.fill(memo[i], -1);
			
			System.out.println(dp(0, 0));
		}
		
		pw.flush();
		pw.close();
	}
}
