import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class UVa10874 {
	
	static int n;
	static int[] l, r;
	static int[][] memo;
	
	public static int dp(int i, int p) {
		if(i == n) {
			int pos = p == 0 ? l[i - 1] : r[i - 1];
			if(pos <= l[i])
				return n - 1 - pos;
			else
				return pos - l[i] + n - 1 - l[i];
		}
		
		if(memo[i][p] != -1)
			return memo[i][p];
		
		int pos = p == 0 ? l[i - 1] : r[i - 1];
		if(pos >= r[i]) {
			return memo[i][p] = 1 + pos - l[i] + dp(i + 1, 0);
		} else if(pos <= l[i]) {
			return memo[i][p] = 1 + r[i] - pos + dp(i + 1, 1);
		} else {
			return memo[i][p] = 1 + r[i] - l[i] + Math.min(pos - l[i] + dp(i + 1, 1), r[i] - pos + dp(i + 1, 0));
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			n = Integer.parseInt(br.readLine());
			if(n == 0)
				break;
			
			l = new int[n + 1];
			r = new int[n + 1];
			
			for(int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				l[i] = Integer.parseInt(st.nextToken()) - 1;
				r[i] = Integer.parseInt(st.nextToken()) - 1;
			}
			
			memo = new int[n + 1][2];
			for(int i = 0; i <= n; i++) {
				memo[i][0] = -1;
				memo[i][1] = -1;
			}
			
			System.out.println(dp(1, 0));
		}
	}
}
