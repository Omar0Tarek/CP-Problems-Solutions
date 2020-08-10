import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa10130 {
	
	static int INF = (int)1e9;
	static int n;
	static int[] w, p;
	static int[][] memo;
	
	public static int dp(int i, int s) {
		if(s < 0)
			return -INF;
		if(s == 0 || i == n)
			return 0;
		
		if(memo[i][s] != -1)
			return memo[i][s];
		
		return memo[i][s] = Math.max(p[i] + dp(i + 1,  s - w[i]), dp(i + 1,  s));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			n = Integer.parseInt(br.readLine());
			p = new int[n];
			w = new int[n];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				p[i] = Integer.parseInt(st.nextToken());
				w[i] = Integer.parseInt(st.nextToken());
			}
			
			memo = new int[n][31];
			for(int i = 0; i < memo.length; i++)
				Arrays.fill(memo[i], -1);
			
			int g = Integer.parseInt(br.readLine());
			long sum = 0;
			while(g-->0) {
				sum += dp(0, Integer.parseInt(br.readLine()));
			}
			
			System.out.println(sum);
		}
	}
}
