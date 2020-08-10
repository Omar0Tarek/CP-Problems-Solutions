import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class UVa11721 {
	// add one dummy node incident to all edges, with edge weight = 0, do bellman ford loop n times instead of n - 1 with this dummy node as source, and then do it for the n + 1th time,
	// , and the nodes that will change will be the parts of the neg cycle 
	static int INF = (int)1e9;
	static int n;
	static ArrayList<Pair>[] adjList;
	static ArrayList<Integer>[] adjListT;
	static TreeSet<Integer> set;
	
	
	public static boolean negCycle() {
		int[] dist = new int[n], distO = new int[n];
		Arrays.fill(dist, INF);
		Arrays.fill(distO, INF);
		
		boolean done = true;
		for(int i = 0; i < n - 1; i++) {
			done = true;
			for(int u = 0; u < n; u++)
				for(Pair v : adjList[u]) {
					distO[v.v] = dist[v.v] = Math.min(dist[v.v], dist[u] + v.w);
					done = false;
				}
			if(done)
				break;
		}
		
		if(done)
			return false;
		
		boolean negCycle = false;
		for(int i = 0; i < n - 1; i++) {
			for(int u = 0; u < n; u++)
				for(Pair v : adjList[u]) {
					if(dist[u] + v.w < dist[v.v]) {
						dist[v.v] = dist[u] + v.w;
						negCycle = true;
					}
				}
		}
		
		if(!negCycle)
			return false;
		
		for(int i = 0; i < n; i++) {
			if(dist[i] < distO[i]) {
				vis = new boolean[n];
				dfs(i);
			}
		}
		
		return true;
	}
	
	static boolean[] vis;
	public static void dfs(int u) {
		vis[u] = true;
		set.add(u);
		
		for(int v : adjListT[u])
			if(!vis[v])
				dfs(v);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		for(int counter = 1; counter <= t; counter++) {
			br.readLine();
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[n];
			adjListT = new ArrayList[n];
			for(int i = 0; i < n; i++) {
				adjList[i] = new ArrayList<Pair>();
				adjListT[i] = new ArrayList<Integer>();
			}
			
			while(m-->0) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken());
				adjList[u].add(new Pair(v, w));
				adjListT[v].add(u);
			}
			
			
			pw.printf("Case %d:", counter);
			
			set = new TreeSet<Integer>();
			if(!negCycle()) {
				pw.print(" impossible");
			} else {
				while(!set.isEmpty())
					pw.print(" " + set.pollFirst());
			}
			
			pw.print("\n");
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