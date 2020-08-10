import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UVa10405 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		while(true) {
			if(!br.ready())
				break;
			
			a = br.readLine();
			b = br.readLine();
			
			pw.println(LCS());
		}
		
		pw.flush();
		pw.close();
	}
	
	static int INF = (int)1e9;
	static String a, b;
	
	public static int LCS() {
		int n = a.length(), m = b.length();
		
		int[][] dp = new int[n + 1][m + 1];
		//dp[0][0] = 0;
		//for(int i = 1; i <= n; i++)
		//	dp[i][0] = -i;
		//for(int i = 1; i <= m; i++)
		//	dp[0][i] = -i;
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				int op1 = dp[i - 1][j - 1] + score(i - 1, j - 1);
				int op2 = dp[i][j - 1] + score(-1, j - 1);
				int op3 = dp[i - 1][j] + score(i - 1, -1);
				dp[i][j] = Math.max(op1, Math.max(op2, op3));
			}
		}
		
		return dp[n][m];
	}
	
	public static int score(int i, int j) {
		if(i == -1 || j == -1)
			return 0;
		if(a.charAt(i) == b.charAt(j))
			return 1;
		return -INF;
	}
	
}


