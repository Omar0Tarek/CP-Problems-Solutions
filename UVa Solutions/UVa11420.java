import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa11420 {
	
	static long[][][] memo;
	
	public static long dp(int i, int s, int l) {
		if(s < 0)
			return 0;
		if(i == 0)
			if(s == 0)
				return 1;
			else
				return 0;
		
		if(memo[i][s][l] != -1)
			return memo[i][s][l];
		
		return memo[i][s][l] = dp(i - 1, l == 1 ? s - 1 : s, 1) + dp(i - 1, s, 0);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		memo = new long[66][66][2];
		for(int i = 0; i < 66; i++)
			for(int j = 0; j < 66; j++)
				Arrays.fill(memo[i][j], -1);
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()), s = Integer.parseInt(st.nextToken());
			if(n + s < 0)
				break;
			
			System.out.println(dp(n, s, 1));
		}
	}
}
