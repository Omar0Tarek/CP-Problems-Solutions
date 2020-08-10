import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

public class UVa610 {
	
	static int n, timer;
	static ArrayList<Integer>[] adjList;
	static int[] dfs_num, dfs_low, parent;
	static PrintWriter pw;
	
	
	public static void findBridges() {
		timer = 0;
		dfs_num = new int[n];
		dfs_low = new int[n];
		parent = new int[n];
		Arrays.fill(dfs_num, -1);
		
		dfs(0);
	}
	
	public static void dfs(int u) {
		dfs_num[u] = dfs_low[u] = timer++;
		
		for(int v : adjList[u]) {
			if(dfs_num[v] == -1) {
				parent[v] = u;
				dfs(v);
				
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
				if(dfs_low[v] > dfs_num[u]) {
					pw.println((u + 1) + " " + (v + 1));
					pw.println((v + 1) + " " + (u + 1));
				} else
					pw.println((u + 1) + " " + (v + 1));
			} else {
				if(parent[u] != v) {
					dfs_low[u] = Math.min(dfs_low[u], dfs_num[v]);
					if(dfs_num[v] < dfs_num[u])
						pw.println((u + 1) + " " + (v + 1));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		int counter = 1;
		while(true) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			if(n + m == 0)
				break;
			
			adjList = new ArrayList[n];
			for(int i = 0; i < n; i++)
				adjList[i] = new ArrayList<Integer>();
			while(m-->0) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1, v = Integer.parseInt(st.nextToken()) - 1;
				adjList[u].add(v);
				adjList[v].add(u);
			}
			
			pw.println(counter++ + "\n");
			findBridges();
			pw.println("#");
		}
		
		pw.flush();
		pw.close();
	}
}
