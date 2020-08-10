import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Arrays;

public class UVa11504 {
	
	static int n;
	static ArrayList<Integer>[] adjList;
	static Stack<Integer> stack;
	static int[] in;
	static boolean[] vis;
	
	static int numSCC, timer;
	static int[] dfs_num, dfs_low, SCC;
	static ArrayList<Integer>[] comp;
	
	public static void SCC() {
		timer = 0;
		numSCC = 0;
		stack = new Stack<Integer>();
		dfs_num = new int[n];
		dfs_low = new int[n];
		SCC = new int[n];
		Arrays.fill(dfs_num, -1);
		Arrays.fill(SCC, -1);
		
		
		for(int i = 0; i < n; i++)
			if(dfs_num[i] == -1)
				dfs_SCC(i);
		
		comp = new ArrayList[numSCC];
		for(int i = 0; i < comp.length; i++)
			comp[i] = new ArrayList<Integer>();
		
		for(int i = 0; i < n; i++)
			comp[SCC[i]].add(i);
			
	}
	
	public static void dfs(int u) {
		vis[u] = true;
		
		for(int v : adjList[u])
			if(!vis[v])
				dfs(v);
	}
	
	public static void dfs_SCC(int u) {
		dfs_num[u] = dfs_low[u] = timer++;
		stack.add(u);
		
		for(int v : adjList[u]) {
			if(dfs_num[v] == -1)
				dfs_SCC(v);
			if(SCC[v] == -1)
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
		}
		
		if(dfs_num[u] == dfs_low[u]) {
			while(true) {
				int x = stack.pop();
				SCC[x] = numSCC;
				if(x == u)
					break;
			}
			numSCC++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			in = new int[n];
			adjList = new ArrayList[n];
			for(int i = 0; i < n; i++)
				adjList[i] = new ArrayList<Integer>();
			while(m-->0) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1, v = Integer.parseInt(st.nextToken()) - 1;
				in[v]++;
				adjList[u].add(v);
			}
			
			SCC();
			
			int counter = 0;
			vis = new boolean[n];
			for(int i = 0; i < n; i++)
				if(in[i] == 0) {
					dfs(i);
					counter++;
				}
			
			for(int i = 0; i < n; i++)
				if(!vis[i]) {
					counter++;
					//for(int v : comp[SCC[i]])
					//	vis[v] = true;
					dfs(i);
				}
			
			pw.println(counter);
		}
		
		pw.flush();
		pw.close();
	}
}
