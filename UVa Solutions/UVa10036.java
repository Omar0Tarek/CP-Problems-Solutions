import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.StringTokenizer;
import java.util.Arrays;

public class UVa10036 {
	
	static int n, k;
	static int[] array;
	static int[][] memo;
	
	public static int dp(int i, int r) {
		if(i == n)
			if(r == 0)
				return 1;
			else
				return 0;
		
		if(memo[i][r + k - 1] != -1)
			return memo[i][r + k - 1];
		
		return memo[i][r + k - 1] = dp(i + 1, (r + (array[i] % k)) % k) | dp(i + 1, (r - (array[i] % k)) % k);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); k = Integer.parseInt(st.nextToken());
			
			array = new int[n];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++)
				array[i] = Integer.parseInt(st.nextToken());
			
			memo = new int[n][2 * k - 1];
			for(int i = 0; i < n; i++)
				Arrays.fill(memo[i], -1);
			
			System.out.println(dp(1, array[0] % k) == 1 ? "Divisible" : "Not divisible");
		}
		
	}
}