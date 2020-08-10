import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class UVa534 {
	
	static int n;
	static double[][] adjMatrix;
	public static double MST() {
		PriorityQueue<Edge> qu = new PriorityQueue<>();
		boolean[] vis = new boolean[n];
		vis[0] = true;
		for(int i = 0; i < n; i++)
			if(i != 0)
				qu.add(new Edge(i, adjMatrix[0][i]));
		
		
		double minimax = 0;
		while(!qu.isEmpty()) {
			Edge e = qu.poll();
			if(!vis[e.v]) {
				vis[e.v] = true;
				
				minimax = Math.max(minimax, e.w);
				if(e.v == 1)
					break;
				
				for(int j = 0; j < n; j++) {
					if(j != e.v)
						qu.add(new Edge(j, adjMatrix[e.v][j]));
				}
			}
		}
		
		return minimax;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		int counter = 1;
		while(true) {
			if(counter != 1)
				br.readLine();
			
			n = Integer.parseInt(br.readLine());
			if(n == 0)
				break;
			
			Pair[] array = new Pair[n];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				array[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			adjMatrix = new double[n][n];
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(i == j)
						continue;
					adjMatrix[i][j] = Math.sqrt(Math.pow(array[i].x - array[j].x, 2) + Math.pow(array[i].y - array[j].y, 2));
				}
			}
			
			pw.printf("Scenario #%d\nFrog Distance = %.3f\n\n", counter++, MST());
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
	
	public static class Edge implements Comparable<Edge> {
		int v;
		double w;
		
		public Edge(int v, double w) {
			this.v = v;
			this.w = w;
		}
		
		public int compareTo(Edge e) {
			return this.w < e.w ? -1 : 1;
		}
	}
}
