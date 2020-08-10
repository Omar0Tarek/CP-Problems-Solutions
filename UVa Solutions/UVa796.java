import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Arrays;


public class UVa796 {
	
	static int n, numBridges, timer;
	static ArrayList<Integer>[] adjList;
	static int[] dfs_num, dfs_low, parent;
	static TreeSet<Pair> bridges;
	
	public static void findBridges() {
		timer = 0;
		
		dfs_num = new int[n];
		dfs_low = new int[n];
		parent = new int[n];
		Arrays.fill(dfs_num, -1);
		
		for(int i = 0; i < n; i++)
			if(dfs_num[i] == -1)
				dfs(i);
	}
	
	public static void dfs(int u) {
		dfs_num[u] = dfs_low[u] = timer++;
		
		for(int v : adjList[u]) {
			if(dfs_num[v] == -1) {
				parent[v] = u;
				dfs(v);
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
				
				if(dfs_low[v] > dfs_num[u]) {
					numBridges++;
					bridges.add(new Pair(u, v));
				}
				
			} else {
				if(parent[u] != v)
					dfs_low[u] = Math.min(dfs_low[u], dfs_num[v]);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		while(true) {
			if(!br.ready())
				break;
			
			n = Integer.parseInt(br.readLine());
			
			adjList = new ArrayList[n];
			for(int i = 0; i < n; i++)
				adjList[i] = new ArrayList<>();
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				String s = st.nextToken();
				int n = Integer.parseInt(s.substring(1, s.length() - 1));
				for(int j = 0; j < n; j++)
					adjList[u].add(Integer.parseInt(st.nextToken()));
			}
			br.readLine();
			
			numBridges = 0;
			bridges = new TreeSet<Pair>();
			findBridges();
			pw.printf("%d critical links\n", numBridges);
			for(Pair p : bridges)
				pw.println(p);
			pw.print("\n");
		}
		
		pw.flush();
		pw.close();
	}
	
	public static class Pair implements Comparable<Pair> {
		int u, v;
		
		public Pair(int u, int v) {
			this.u = Math.min(u, v);
			this.v = Math.max(u, v);
		}
		
		public int compareTo(Pair p) {
			if(this.u == p.u)
				return this.v - p.v;
			return this.u - p.u;
		}
		
		public String toString() {
			return u + " - " + v;
		}
	}
}