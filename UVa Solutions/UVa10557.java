import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import java.util.ArrayList;
import java.util.Arrays;

public class UVa10557 {
	
	static int INF = (int)1e9;
	static int n;
	static ArrayList<Integer>[] doors;
	static ArrayList<Pair>[] adjList;
	static int[] pow, dist;
	
	public static boolean pass() {
		dist = new int[n];
		Arrays.fill(dist, -INF);
		dist[0] = 100;
		
		for(int i = 0; i < n - 1; i++) {
			boolean stop = true;
			
			for(int u = 0; u < n; u++)
				for(Pair v : adjList[u]) {
					if(dist[u] + v.w > dist[v.v]) {
						dist[v.v] = dist[u] + v.w;
						stop = false;
					}
				}
			
			if(stop)
				break;
		}
		
		boolean alive = true;
		for(int i = 0; i < n; i++)
			if(dist[i] <= 0)
				alive = false;
		
		boolean posCycle = false;
		for(int u = 0; u < n; u++)
			for(Pair v : adjList[u]) {
				if(dist[u] + v.w > dist[v.v]) {
					dist[v.v] = dist[u] + v.w;
					posCycle = true;
				}
			}
		
		return posCycle || alive;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		int counter = 0;
		while(true) {
			n = Integer.parseInt(br.readLine());
			if(n == -1)
				break;
			
			pow = new int[n];
			doors = new ArrayList[n];
			for(int i = 0; i < n; i++)
				doors[i] = new ArrayList<Integer>();
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				pow[i] = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				while(c-->0)
					doors[i].add(Integer.parseInt(st.nextToken()) - 1);
			}
			
			adjList = new ArrayList[n];
			for(int i = 0; i < n; i++)
				adjList[i] = new ArrayList<Pair>();
			
			for(int i = 0; i < n; i++)
				for(int j : doors[i])
					adjList[i].add(new Pair(j, pow[j]));
			
			pw.println(pass() ? "winnable" : "hopeless");
			
			String s = br.readLine();
			if(s.equals("-1"))
				break;
		}
		
		pw.flush();
		pw.close();
	}
	
	public static class Pair {
		int v, w;
		
		public Pair(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
}
