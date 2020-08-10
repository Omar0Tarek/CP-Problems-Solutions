import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.Arrays;

public class UVa11472 {
	
	static int MOD = (int)1e9 + 7;
	static int n;
	static int[][][] memo;
	
	public static int dp(int i, int d, int msk) {
		if(i == 0)
			if(msk == ((1 << n) - 1))
				return 1;
			else
				return 0;
		
		if(memo[i][d][msk] != -1)
			return memo[i][d][msk];
		
		if(d == 0)
			return memo[i][d][msk] = dp(i - 1, d + 1, msk | (1 << (d + 1))) % MOD;
		else if(d == n - 1)
			return memo[i][d][msk] = dp(i - 1, d - 1, msk | (1 << (d - 1))) % MOD;
		else
			return memo[i][d][msk] = (dp(i - 1, d - 1, msk | (1 << (d - 1))) % MOD + dp(i - 1, d + 1, msk | (1 << (d + 1))) % MOD) % MOD;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		PrintWriter pw = new PrintWriter(System.out);
		
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			memo = new int[m + 1][n][1 << n];
			for(int i = 0; i < m + 1; i++)
				for(int j = 0; j < n; j++)
					Arrays.fill(memo[i][j], -1);
			
			long sum = 0;
			for(int i = n; i <= m; i++) {
				for(int j = 1; j < n; j++) {
					//System.out.println(dp(i, j, 1 << j));
					sum = (sum + (dp(i - 1, j, 1 << j)) % MOD) % MOD;
				}
			}
			
			pw.println(sum);
		}
		
		pw.flush();
		pw.close();
	}
}
