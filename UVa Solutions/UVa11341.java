import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.StringTokenizer;
import java.util.Arrays;

public class UVa11341 {
	
	static int INF = (int)1e9;
	static int n, m;
	static int[][] array;
	static int[][] memo;
	
	public static int dp(int i, int s) {
		if(s < 0)
			return -INF;
		if(i == n)
			return 0;
		if(s == 0)
			return -INF;
		
		if(memo[i][s] != -1)
			return memo[i][s];
		
		int max = -INF;
		for(int j = 0; j < array[i].length; j++) {
			if(array[i][j] > 4) {
				max = Math.max(max, array[i][j] + dp(i + 1, s - (j + 1)));
			}
		}
		
		return memo[i][s] = max;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());
			
			array = new int[n][m];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < m; j++) {
					array[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			memo = new int[n][m + 1];
			for(int i = 0; i < n; i++)
				Arrays.fill(memo[i], -1);
			
			int x = dp(0, m);
			System.out.printf(x < 0 ? "Peter, you shouldn't have played billiard that much.\n" : ("Maximal possible average mark - %.2f.\n"), x * 1.0 / n);
		}
	}
}
