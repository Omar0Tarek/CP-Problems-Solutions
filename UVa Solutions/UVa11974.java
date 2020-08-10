import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa11974 {
	
	static int INF = (int)1e9, limit;
	static int n, m;
	static ArrayList<Integer>[] adjList;
	static int[][] memo;
	
	public static int dp(int i, int msk) {
		if(msk == limit)
			return 0;
		if(i == m)
			return INF;
		if(memo[i][msk] != -1)
			return memo[i][msk];
		
		int mskCopy = msk;
		for(int j : adjList[i])
			mskCopy ^= (1<<j);
		
		return memo[i][msk] = Math.min(1 + dp(i + 1, mskCopy), dp(i + 1, msk));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		for(int counter = 1; counter <= t; counter++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[m];
			for(int i = 0; i < m; i++)
				adjList[i] = new ArrayList<Integer>();
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					if(st.nextToken().equals("1"))
						adjList[i].add(j);
				}
			}
			
			limit = (int)Math.pow(2, n) - 1;
			memo = new int[m][limit + 1];
			for(int i = 0; i < m; i++)
				Arrays.fill(memo[i], -1);
			
			
			int ans = dp(0, 0);
			pw.printf("Case %d: %s\n", counter, ans >= INF ? "IMPOSSIBLE" : (ans + ""));
		}
		
		pw.flush();
		pw.close();		
	}
}
