import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class UVa10765 {
	
	static int n;
	static ArrayList<Integer>[] adjList;
	static int[] dfs_num, dfs_low, parent, pValue;
	static int timer;
	
	public static void findBridges() {
		timer = 0;
		dfs_num = new int[n];
		dfs_low = new int[n];
		parent = new int[n];
		pValue = new int[n];
		Arrays.fill(dfs_num , -1);
		
		dfs(0);
	}
	
	public static void dfs(int u) {
		dfs_num[u] = dfs_low[u] = timer++;
		
		int counter = 0;
		for(int v : adjList[u]) {
			if(dfs_num[v] == -1) {
				parent[v] = u;
				dfs(v);
				
				if(dfs_low[v] > dfs_num[u]) {
					pValue[v]++;
				}
				
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
				counter++;
			} else
				if(parent[u] != v) {
					dfs_low[u] = Math.min(dfs_low[u], dfs_num[v]);
				}
		}
		
		pValue[u] = counter;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			if(n + m == 0)
				break;
			
			adjList = new ArrayList[n];
			for(int i = 0; i < n; i++)
				adjList[i] = new ArrayList<Integer>();
			
 			while(true) {
 				st = new StringTokenizer(br.readLine());
 				int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());
 				if(u + v == -2)
 					break;
 				
 				adjList[u].add(v);
 				adjList[v].add(u);
 			}
 			
 			findBridges();
 			
 			ArrayList<Pair> out = new ArrayList<>();
 			for(int i = 0; i < n; i++)
 				out.add(new Pair(i, pValue[i]));
 			
 			Collections.sort(out);
 			for(int i = 0; i < m; i++)
 				pw.println(out.get(i));
		}
		
		pw.flush();
		pw.close();
	}
	
	public static class Pair implements Comparable<Pair> {
		int i, n;
		
		public Pair(int i, int n) {
			this.i = i;
			this.n = n;
		}
		
		public int compareTo(Pair p) {
			if(this.n == p.n)
				return this.i - p.i;
			return p.n - this.n;
		}
		
		public String toString() {
			return this.i + " " + this.n;
		}
	}
}
