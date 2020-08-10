import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class UVa532 {
	
	static int l, r, c;
	static int xs, ys, zs, xt, yt, zt;
	static char grid[][][];
	static int[] dx = {0, 0, 0, 0, 1, -1};
	static int[] dy = {0, 0, 1, -1, 0, 0};
	static int[] dz = {1, -1, 0, 0, 0, 0};
	
	public static int bfs() {
		Queue<Pair> qu = new LinkedList<>();
		grid[xs][ys][zs] = 'v';
		qu.add(new Pair(xs, ys, zs, 0));
		
		while(!qu.isEmpty()) {
			Pair u = qu.poll();
			for(int i = 0; i < dx.length; i++) {
				int xn = u.x + dx[i], yn = u.y + dy[i], zn = u.z + dz[i];
				if(isValid(xn, yn, zn)) {
					if(exit(xn, yn, zn))
						return u.w + 1;
					grid[xn][yn][zn] = 'v';
					qu.add(new Pair(xn, yn, zn, u.w + 1));
				}
			}
		}
		return -1;
	}
	
	public static boolean isValid(int x, int y, int z) {
		return x > -1 && x < r && y > -1 && y < c && z > -1 && z < l && (grid[x][y][z] == '.' || grid[x][y][z] == 'E');
	}
	
	public static boolean exit(int x, int y, int z) {
		return xt == x && yt == y && zt == z;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			l = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			if(l + r + c == 0)
				break;
			
			grid = new char[r][c][l];
			for(int k = 0; k < l; k++) {
				for(int i = 0; i < r; i++) {
					String s = br.readLine();
					for(int j = 0; j < c; j++) {
						grid[i][j][k] = s.charAt(j);
						if(s.charAt(j) == 'S') {
							xs = i;
							ys = j;
							zs = k;
						} else if(s.charAt(j) == 'E') {
							xt = i;
							yt = j;
							zt = k;
						}
					}
				}
				br.readLine();
			}
			
			int ans = bfs();
			if(ans != -1)
				pw.printf("Escaped in %d minute(s).\n", ans);
			else
				pw.printf("Trapped!\n");
		}
		
		pw.flush();
		pw.close();
	}
	
	public static class Pair {
		int x, y, z, w;
		
		public Pair(int x, int y, int z, int w) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.w = w;
		}
	}
}
