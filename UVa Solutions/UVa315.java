import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import java.util.ArrayList;
import java.util.Arrays;

public class UVa315 {
	
	static int n;
	static ArrayList<Integer>[] adjList;
	static int[] dfs_num, dfs_low, parent;
	static int timer, root, rootChildren;
	static boolean[] artPoints;
	
	public static void articulationPoints() {
		timer = 0;
		dfs_num = new int[n];
		dfs_low = new int[n];
		parent = new int[n];
		artPoints = new boolean[n];
		Arrays.fill(dfs_num, -1);
		
		for(int i = 0; i < n; i++)
			if(dfs_num[i] == -1) {
				root = i;
				rootChildren = 0;
				dfs(i);
				if(rootChildren <= 1)
					artPoints[root] = false;
			}
	}
	
	public static void dfs(int u) {
		dfs_num[u] = dfs_low[u] = timer++;
		
		for(int v : adjList[u]) {
			if(parent[u] == v)
				continue;
			if(dfs_num[v] == -1) {
				if(root == u) rootChildren++;
				
				parent[v] = u;
				dfs(v);
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
				
				if(dfs_low[v] >= dfs_num[u])
					artPoints[u] = true;
			} else {
				dfs_low[u] = Math.min(dfs_low[u], dfs_num[v]);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		while(true) {
			n = Integer.parseInt(br.readLine());
			if(n == 0)
				break;
			
			adjList = new ArrayList[n];
			for(int i = 0; i < n; i++)
				adjList[i] = new ArrayList<Integer>();
			
			while(true) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1;
				if(u == -1)
					break;
				while(st.hasMoreTokens()) {
					int v = Integer.parseInt(st.nextToken()) - 1;
					adjList[u].add(v);
					adjList[v].add(u);
				}
			}
			
			articulationPoints();
			int points = 0;
			for(int i = 0; i < artPoints.length; i++)
				if(artPoints[i])
					points++;
			
			pw.println(points);
		}
		
		pw.flush();
		pw.close();
	}
}
