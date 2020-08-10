import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

public class UVa10977 {
	
	static int INF = (int)1e9;
	static int r, c;
	static boolean[][] grid;
	static int[][] dist;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void bfs() {
		dist = new int[r][c];
		for(int i = 0; i < r; i++)
			Arrays.fill(dist[i], INF);
		
		Queue<Pair> qu = new LinkedList<>();
		qu.add(new Pair(0, 0));
		grid[0][0] = false;
		dist[0][0] = 0;
		
		while(!qu.isEmpty()) {
			Pair u = qu.poll();
			for(int i = 0; i < dx.length; i++) {
				int xn = u.x + dx[i], yn = u.y + dy[i];
				if(isValid(xn, yn)) {
					grid[xn][yn] = false;
					dist[xn][yn] = dist[u.x][u.y] + 1;
					qu.add(new Pair(xn, yn));
				}
			}
		}
	}
	
	public static boolean isValid(int x, int y) {
		return x > -1 && x < r && y > -1 && y < c && grid[x][y];
	}
	
	public static double dist(int x1, int y1, int x2, int y2) {
		return Math.hypot(1.0 * Math.abs(x1 - x2), 1.0 * Math.abs(y1 - y2));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			if(r + c == 0)
				break;
			
			grid = new boolean[r][c];
			for(int i = 0; i < r; i++)
				Arrays.fill(grid[i], true);
			
			int m = Integer.parseInt(br.readLine());
			while(m-->0) {
				st = new StringTokenizer(br.readLine());
				grid[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = false;
			}
			
			int n = Integer.parseInt(br.readLine());
			while(n-->0) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()) - 1, y = Integer.parseInt(st.nextToken()) - 1, l = Integer.parseInt(st.nextToken());
				for(int i = Math.max(0,  x - l); i < Math.min(r, x + l + 1); i++) {
					for(int j = Math.max(0,  y - l); j < Math.min(c, y + l + 1); j++) {
						if(l - dist(x, y, i, j) >= 0)
							grid[i][j] = false;
					}
				}
			}
			
			bfs();
			pw.println(dist[r - 1][c - 1] == INF ? "Impossible." : dist[r - 1][c - 1]);
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
