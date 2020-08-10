import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class UVa11624 {
	
	static int r, c;
	static char[][] maze;
	static int xi, yi;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	public static int bfs() {
		Queue<Triple> qu = new LinkedList<>();
		for(int i = 0; i < r; i++)
			for(int j = 0; j < c; j++) {
				if(maze[i][j] == 'F') {
					qu.add(new Triple(i, j, -1));
					maze[i][j] = 'v';
				}
			}
		
		maze[xi][yi] = 'v';
		qu.add(new Triple(xi, yi, 0));
		
		while(!qu.isEmpty()) {
			Triple u = qu.poll();
			int x = u.x, y = u.y, w = u.w;
			for(int i = 0; i < dx.length; i++) {
				int xn = x + dx[i], yn = y + dy[i];
				if(w < 0) {
					if(isValid(xn, yn)) {
						maze[xn][yn] = 'v';
						qu.add(new Triple(xn, yn, -1));
					}
				} else {
					if(border(x, y))
						return w + 1;
					if(isValid(xn, yn)) {
						maze[xn][yn] = 'v';
						qu.add(new Triple(xn, yn, w + 1));
					}
				}
			}
		}
		
		return -1;
	}
	
	public static boolean isValid(int x, int y) {
		return x > -1 && x < r && y > -1 && y < c && maze[x][y] == '.';
	}
	
	public static boolean border(int x, int y) {
		return x == 0 || x == r - 1 || y == 0 || y == c - 1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			maze = new char[r][c];
			for(int i = 0; i < r; i++) {
				String s = br.readLine();
				for(int j = 0; j < c; j++) {
					maze[i][j] = s.charAt(j);
					if(maze[i][j] == 'J') {
						xi = i;
						yi = j;
						maze[i][j] = '.';
					}
				}
			}
			
			int dist = bfs();
			pw.println(dist == -1 ? "IMPOSSIBLE" : dist);
		}
		
		pw.flush();
		pw.close();
		
	}
	
	public static class Triple {
		int x, y, w;
		
		public Triple(int x, int y, int w) {
			this.x = x;
			this.y = y;
			this.w = w;
		}
	}
}
