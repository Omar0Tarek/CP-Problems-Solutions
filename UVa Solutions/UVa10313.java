import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class UVa10313 {
	
	static long[][][] memo;
	
	public static long dp(int i, int s, int c) {
		if(s < 0)
			return 0;
		if(c == 0 && s == 0)
			return 1;
		if(i > 300 || s == 0 || c == 0)
			return 0;
		
		if(memo[i][s][c] != -1)
			return memo[i][s][c];
		
		return memo[i][s][c] = dp(i, s - i, c - 1) + dp(i + 1, s, c);
	}
	
	public static void main(String[] ignore) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		memo = new long[301][301][301];
		for(int i = 0; i < memo.length; i++)
			for(int j = 0; j < memo[i].length; j++)
				Arrays.fill(memo[i][j], -1);
		
		while(true) {
			if(!br.ready())
				break;
			
			String[] args = br.readLine().split(" ");
			
			int l = 0, r = 300;
			
			if(args.length == 2) {
				r = Integer.parseInt(args[1]);
			} else if(args.length == 3) {
				l = Integer.parseInt(args[1]);
				r = Integer.parseInt(args[2]);
			}
			
			if(l > 300)
				l = 300;
			if(r > 300)
				r = 300;
			
			long sum = 0;
			int n = Integer.parseInt(args[0]);
			for(int i = l; i <= r; i++)
				sum += dp(1, n, i);
			System.out.println(sum);
			
		}
	}
}
