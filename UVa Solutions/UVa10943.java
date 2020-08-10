import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa10943 {
	
	static int[][] memo = new int[101][101];
	
	/*
	public static int dp(int i, int r, int k) {
		if(r < 0)
			return 0;
		
		if(k == 0)
			if(r == 0)
				return 1;
			else
				return 0;
		
		if(i == -1)
			return 0;
		
		if(memo[i][r][k] != -1)
			return memo[i][r][k];
		
		return memo[i][r][k] = dp(i, r - i, k - 1) + dp(i - 1, r, k);
	}
	*/
	
	public static int dp(int n, int k) {
		if(n < 0)
			return 0;
		
		if(k == 0)
			if(n == 0)
				return 1;
			else
				return 0;
		
		
		if(memo[n][k] != -1)
			return memo[n][k];
		
		int sum = 0;
		for(int i = 0; i <= n; i++)
			sum += dp(n - i, k - 1);
		
		return memo[n][k] = sum % 1000000;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		for(int i = 0; i < 101; i++)
			Arrays.fill(memo[i], -1);
		
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
			if(n + k == 0)
				break;
			/*
			for(int i = 0; i < 101; i++)
				Arrays.fill(memo[i], -1);
			*/
			
			System.out.println(dp(n, k) % 1000000);
			
		}
	}
}
