import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

public class UVa10653 {
	
	static int r, c;
	static int xi, yi, xf, yf;
	static char[][] grid;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	public static int BFS() {
		Queue<Node> qu = new LinkedList<Node>();
		grid[xi][yi] = 'x';
		qu.add(new Node(xi, yi, 0));
		
		while(!qu.isEmpty()) {
			Node u = qu.poll();
			int x = u.x, y = u.y;
			for(int i = 0; i < dx.length; i++) {
				int xn = x + dx[i], yn = y + dy[i];
				if(isValid(xn, yn)) {
					if(xn == xf && yn == yf)
						return u.l + 1;
					grid[xn][yn] = 'x';
					qu.add(new Node(xn, yn, u.l + 1));
				}
			}
		}
		
		return -1;
	}
	
	public static boolean isValid(int x, int y) {
		return x >= 0 && x < r && y >= 0 && y < c && grid[x][y] == '.';
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
			
			
			grid = new char[r][c];
			for(int i = 0; i < r; i++)
				Arrays.fill(grid[i], '.');
			
			int m = Integer.parseInt(br.readLine());
			while(m-->0) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken()), c =  Integer.parseInt(st.nextToken());
				while(c-->0)
					grid[r][Integer.parseInt(st.nextToken())] = '#';
			}
			
			st = new StringTokenizer(br.readLine());
			xi = Integer.parseInt(st.nextToken());
			yi = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			xf = Integer.parseInt(st.nextToken());
			yf = Integer.parseInt(st.nextToken());
			
			pw.println(BFS());
		}
		
		pw.flush();
		pw.close();
	}
	
	public static class Node {
		int x, y, l;
		
		public Node(int x, int y, int l) {
			this.x = x;
			this.y = y;
			this.l = l;
		}
	}
}
