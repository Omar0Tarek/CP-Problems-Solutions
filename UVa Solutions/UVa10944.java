import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.Arrays;

public class UVa10944 {
	
	static int INF = (int)1e9;
	static int n, m, counter, limit, larryPos;
	static int[][] dist;
	static int[][] memo;
	
	public static int dp(int msk, int i) {
		if(msk == limit)
			return dist[i][larryPos];
		if(memo[msk][i] != -1)
			return memo[msk][i];
		
		int min = INF;
		for(int j = 0; j < counter; j++)
			if((msk & (1 << j)) != (1 << j))
				min = Math.min(min, dist[i][j] + dp(msk | (1 << j), j));
		
		return memo[msk][i] = min;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		while(true) {
			if(!br.ready())
				break;
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());
			Pair[] nuts = new Pair[16];
			larryPos = 0;
			counter = 0;
			for(int i = 0; i < n; i++) {
				String s = br.readLine();
				for(int j = 0; j < m; j++) {
					if(s.charAt(j) == '#') {
						nuts[counter++] = new Pair(i, j);
					} else if(s.charAt(j) == 'L') {
						nuts[counter++] = new Pair(i, j);
						larryPos = counter - 1;
					}
				}
			}
			
			dist = new int[counter][counter];
			for(int i = 0; i < counter; i++) {
				for(int j = 0; j < counter; j++) {
					dist[i][j] = Math.max(Math.abs(nuts[i].x - nuts[j].x), Math.abs(nuts[i].y - nuts[j].y));
				}
			}
			
			memo = new int[(1 << counter) - 1][counter];
			for(int i = 0; i < memo.length; i++)
				Arrays.fill(memo[i], -1);
			
			limit = (1 << counter) - 1;
			pw.println(dp(1 << larryPos, larryPos));
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
