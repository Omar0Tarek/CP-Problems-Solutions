import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.Arrays;

public class UVa990 {
	
	static int INF = (int)1e9;
	static int n, w;
	static int[] d, v;
	static int[][] memo;
	
	public static int dp(int i, int r) {
		if(r < 0)
			return -INF;
		if(r == 0 || i == n)
			return 0;
		if(memo[i][r] != -1)
			return memo[i][r];
		
		return memo[i][r] = Math.max(dp(i + 1, r), v[i] + dp(i + 1, r - 3 * d[i] * w));
	}
	
	static StringBuilder treasures;
	static int numTreasures;
	public static void printPath(int i, int r) {
		if(i == n || r == 0)
			return;
		
		if(memo[i][r] == v[i] + dp(i + 1, r - 3 * d[i] * w)) {
			numTreasures++;
			treasures.append(d[i] + " " + v[i] + "\n");
			printPath(i + 1, r - 3 * d[i] * w);
		} else {
			printPath(i + 1, r);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		int counter = 0;
		while(true) {
			if(!br.ready())
				break;
			
			if(counter++ != 0) {
				br.readLine();
				pw.print("\n");
			}
			
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			n = Integer.parseInt(br.readLine());
			
			d = new int[n];
			v = new int[n];
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				d[i] = Integer.parseInt(st.nextToken());
				v[i] = Integer.parseInt(st.nextToken());
			}
			
			memo = new int[n][t + 1];
			for(int i = 0; i < n; i++)
				Arrays.fill(memo[i], -1);
			
			pw.println(dp(0, t));
			
			numTreasures = 0;
			treasures = new StringBuilder();
			printPath(0, t);
			
			pw.printf("%d\n%s", numTreasures, treasures);
		}
		
		pw.flush();
		pw.close();
	}
}
