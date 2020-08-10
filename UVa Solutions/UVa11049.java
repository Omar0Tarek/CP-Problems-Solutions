import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class UVa11049 {
	
	static int xs, ys, xt, yt;
	static char[][] parent;
	static Cell[][] directions;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static char[] d = {'E', 'W', 'S', 'N'};
		
	public static void bfs() {
		boolean[][] vis = new boolean[6][6];
		parent = new char[6][6];
		
		Queue<Pair> qu = new LinkedList<Pair>();
		qu.add(new Pair(xs, ys));
		vis[xs][ys] = true;
		parent[xs][ys] = '.';
		
		
		whileLoop : while(!qu.isEmpty()) {
			Pair u = qu.poll();
			int x = u.x, y = u.y;
			for(int i = 0; i < dx.length; i++) {
				if(directions[x][y].check(d[i])) {
					int xn = x + dx[i], yn = y + dy[i];
					if(isValid(xn, yn) && !vis[xn][yn]) {
						vis[xn][yn] = true;
						parent[xn][yn] = d[i];
						if(xn == xt && y == yt)
							break whileLoop;
						qu.add(new Pair(xn, yn));
					}
				}
			}
		}
	}
	
	
	public static boolean isValid(int x, int y) {
		return x > -1 && x < 6 && y > -1 && y < 6;
	}
	
	public static String getParents(int x, int y) {
		if(parent[x][y] == '.')
			return "";
		int xn = x, yn = y;
		switch(parent[x][y]) {
			case 'N' : xn++; break;
			case 'S' : xn--; break;
			case 'E' : yn--; break;
			case 'W' : yn++; break;
		}
		return getParents(xn, yn) + "" + parent[x][y];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			ys = Integer.parseInt(st.nextToken()) - 1;
			xs = Integer.parseInt(st.nextToken()) - 1;
			if(xs + ys == -2)
				break;
			st = new StringTokenizer(br.readLine());
			yt = Integer.parseInt(st.nextToken()) - 1;
			xt = Integer.parseInt(st.nextToken()) - 1;
			
			directions = new Cell[6][6];
			for(int i = 0; i < 6; i++)
				for(int j = 0; j < 6; j++)
					directions[i][j] = new Cell();
			
			for(int i = 0; i < 3; i++) {
				st = new StringTokenizer(br.readLine());
				int yi = Integer.parseInt(st.nextToken()), xi = Integer.parseInt(st.nextToken()), yf = Integer.parseInt(st.nextToken()), xf = Integer.parseInt(st.nextToken());
				if(yi == yf) {
					for(; xi < xf; xi++) {
						if(isValid(xi, yi))
							directions[xi][yi].l = false;
						if(isValid(xi, yi - 1))
							directions[xi][yi - 1].r = false;
					}
				} else {
					for(; yi < yf; yi++) {
						if(isValid(xi, yi))
							directions[xi][yi].u = false;
						if(isValid(xi - 1, yi))
							directions[xi - 1][yi].d = false;
					}
				}
			}
			
			bfs();
			pw.println(getParents(xt, yt));
		}
		
		pw.flush();
		pw.close();
	}
	
	public static class Cell {
		boolean r, l, u, d;
		
		public Cell() {
			this.r = this.l = this.u = this.d = true;
		}
		
		public boolean check(char d) {
			switch(d) {
				case 'N' : return u;
				case 'S' : return this.d;
				case 'E' : return r;
				case 'W' : return l;
				default : return false;
			}
		}
	}
	
	public static class Pair {
		int x, y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
