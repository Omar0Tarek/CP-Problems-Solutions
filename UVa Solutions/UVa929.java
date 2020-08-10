import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Arrays;

public class UVa929 {
	
	static int INF = (int)1e9;
	static int r, c;
	static int[][] maze;
	static int[][] dist;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void Dijkstra() {
		dist = new int[r][c];
		for(int i = 0; i < r; i++)
			Arrays.fill(dist[i], INF);
		dist[0][0] = maze[0][0];
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(0, 0, dist[0][0]));
		while(!pq.isEmpty()) {
			Node u = pq.poll();
			if(u.d > dist[u.x][u.y])
				continue;
			
			int d = u.d;
			for(int i = 0; i < dx.length; i++) {
				int xn = u.x + dx[i], yn = u.y + dy[i];
				if(isValid(xn, yn)) {
					if(d + maze[xn][yn] < dist[xn][yn]) {
						dist[xn][yn] = d + maze[xn][yn];
						pq.add(new Node(xn, yn, dist[xn][yn]));
					}
				}
			}
		}
	}
	
	public static boolean isValid(int x, int y) {
		return x > -1 && x < r && y > -1 && y < c;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			r = Integer.parseInt(br.readLine());
			c = Integer.parseInt(br.readLine());
			maze = new int[r][c];
			for(int i = 0; i < r; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < c; j++)
					maze[i][j] = Integer.parseInt(st.nextToken());
			}
			
			Dijkstra();
			pw.println(dist[r - 1][c - 1]);
		}
		
		pw.flush();
		pw.close();
	}
	
	public static class Node implements Comparable<Node> {
		int x, y, d;
		
		public Node(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
		
		public int compareTo(Node n) {
			return this.d - n.d;
		}
	}
}
