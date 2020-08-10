import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa1250 {
	
	static double INF = 1e9;
	static int n;
	static int[] p;
	static double[][] dist;
	static double[] memo;
	
	public static double dp(int i) {
		if(i == n - 1)
			return 0;
		if(Math.abs(memo[i] - -1) > 1e-7)
			return memo[i];
		
		double min = INF;
		int penalty = 0;
		for(int j = i + 1; j < n; j++) {
			min = Math.min(min, 1 + dist[i][j] + penalty + dp(j));
			if(j < n - 1)
				penalty += p[j - 1];
		}
		
		return memo[i] = min;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		PrintWriter pw = new PrintWriter(System.out);
		
		while(true) {
			n = Integer.parseInt(br.readLine());
			if(n == 0)
				break;
			
			n += 2;
			
			Pair[] pos = new Pair[n];
			p = new int[n - 2];
			
			pos[0] = new Pair(0, 0);
			pos[n - 1] = new Pair(100, 100);
			for(int i = 1; i < n - 1; i++) {
				st = new StringTokenizer(br.readLine());
				pos[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				p[i - 1] = Integer.parseInt(st.nextToken());
			}
			
			dist = new double[n][n];
			for(int i = 0; i < n; i++) {
				for(int j = i + 1; j < n; j++) {
					double x = Math.hypot(1.0 * Math.abs(pos[i].x - pos[j].x), 1.0 * Math.abs(pos[i].y - pos[j].y));
					dist[i][j] = dist[j][i] = x;
				}
			}
			
			memo = new double[n];
			Arrays.fill(memo, -1);
			
			pw.printf("%.3f\n", dp(0));
		}
		
		pw.flush();
		pw.close();
	}
	
	public static class Pair {
		int x, y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
