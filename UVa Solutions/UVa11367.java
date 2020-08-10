import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;

public class UVa11367 {
	
	static int INF = (int)1e9;
	static int n;
	static int[] cost;
	static ArrayList<Pair>[] adjList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		cost = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++)
			cost[i] = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList[n];
		for(int i = 0; i < n; i++)
			adjList[i] = new ArrayList<Pair>();
		while(m-->0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken());
			adjList[u].add(new Pair(v, w));
			adjList[v].add(new Pair(u, w));
		}
		
		int q = Integer.parseInt(br.readLine());
		while(q-->0) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken()), s = Integer.parseInt(st.nextToken()), t = Integer.parseInt(st.nextToken());
			
		}
		
		pw.flush();
		pw.close();
		
	}
	
	public static class Pair {
		int u, w;
		
		public Pair(int u, int w) {
			this.u = u;
			this.w = w;
		}
	}
	
	public static class Triple implements Comparable<Triple> {
		int v, c, f;
		
		public Triple(int v, int c, int f) {
			this.v = v;
			this.c = c;
			this.f = f;
		}
		
		public int compareTo(Triple t) {
			return this.c - t.c;
		}
	}
}
