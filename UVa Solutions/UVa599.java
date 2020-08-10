import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.ArrayList;

public class UVa599 {
	
	static ArrayList<Integer>[] adjList;
	static boolean[] vis;
	static boolean[] isNode;
	
	public static void dfs(int u) {
		vis[u] = true;
		
		for(int v : adjList[u])
			if(!vis[v])
				dfs(v);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			ArrayList<String> edges = new ArrayList<>();
			while(true) {
				String s = br.readLine();
				if(s.charAt(0) == '*')
					break;
				edges.add(s);
			}
			
			String[] nodes = br.readLine().split(",");
			isNode = new boolean[26];
			for(int i = 0; i < nodes.length; i++)
				isNode[nodes[i].charAt(0) - 65] = true;
			
			adjList = new ArrayList[26];
			for(int i = 0; i < adjList.length; i++)
				adjList[i] = new ArrayList<Integer>();
			
			for(int i = 0; i < edges.size(); i++) {
				int u = edges.get(i).charAt(1) - 65, v = edges.get(i).charAt(3) - 65;
				adjList[u].add(v);
				adjList[v].add(u);
			}
			
			vis = new boolean[26];
			int trees = 0, acrons = 0;
			for(int i = 0; i < vis.length; i++)
				if(isNode[i])
					if(!vis[i]) {
						if(adjList[i].size() == 0)
							acrons++;
						else {
							trees++;
							dfs(i);
						}
					}
			
			System.out.printf("There are %d tree(s) and %d acorn(s).\n", trees, acrons);
		}
		
	}
}
