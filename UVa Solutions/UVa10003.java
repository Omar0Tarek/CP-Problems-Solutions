import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa10003 {
	
	static boolean[] cut;
	static int[][] memo;
	static int INF = (int)1e9;
	
	public static int dp(int start, int end) {
		if(memo[start][end] != -1)
			return memo[start][end];
		
		boolean flag = true;
		int min = INF;
		for(int i = start + 1; i < end; i++) {
			if(cut[i]) {
				flag = false;
				min = Math.min(min, (end - start) + dp(start, i) + dp(i, end));
			}
		}
		
		if(flag)
			return 0;
		
		return memo[start][end] = min;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			int l = Integer.parseInt(br.readLine());
			if(l == 0)
				break;
			
			cut = new boolean[l + 1];
			memo = new int[l + 1][l + 1];
			for(int i = 0; i < l + 1; i++)
				Arrays.fill(memo[i], -1);
			
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			while(n-->0)
				cut[Integer.parseInt(st.nextToken())] = true;
			
			System.out.printf("The minimum cutting is %d.\n", dp(0, l));
		}
	}
}
