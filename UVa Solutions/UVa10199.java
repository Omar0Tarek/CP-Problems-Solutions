import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.util.Arrays;


public class UVa10199 {
	
	static int n, timer, root, rootChildren;
	static ArrayList<Integer>[] adjList;
	static int[] dfs_num, dfs_low, parent;
	static TreeMap<String, Integer> map;
	static String[] map2;
	static TreeSet<String> artPoints;
	
	public static void findArtPoints() {
		timer = 0;
		dfs_num = new int[n];
		dfs_low = new int[n];
		parent = new int[n];
		Arrays.fill(dfs_num, -1);
		
		for(int i = 0; i < n; i++)
			if(dfs_num[i] == -1) {
				root = i;
				rootChildren = 0;
				dfs(i);
				
				if(rootChildren <= 1)
					artPoints.remove(map2[root]);
			}
	}
	
	public static void dfs(int u) {
		dfs_num[u] = dfs_low[u] = timer++;
		
		for(int v : adjList[u]) {
			if(dfs_num[v] == -1) {
				if(root == u)
					rootChildren++;
				
				parent[v] = u;
				dfs(v);
				
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
				if(dfs_low[v] >= dfs_num[u])
					artPoints.add(map2[u]);
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
		
		int counter = 1;
		while(true) {
			n = Integer.parseInt(br.readLine());
			if(n == 0)
				break;
			
			if(counter > 1)
				pw.print("\n");
			
			map = new TreeMap<>();
			map2 = new String[n];
			for(int i = 0; i < n; i++) {
				String s = br.readLine();
				map.put(s, i);
				map2[i] = s;
			}
			
			adjList = new ArrayList[n];
			for(int i = 0; i < n; i++)
				adjList[i] = new ArrayList<Integer>();
			
			int m = Integer.parseInt(br.readLine());
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int u = map.get(st.nextToken()), v = map.get(st.nextToken());
				adjList[u].add(v);
				adjList[v].add(u);
			}
			
			artPoints = new TreeSet<String>();
			findArtPoints();
			pw.printf("City map #%d: %d camera(s) found\n", counter++, artPoints.size());
			while(!artPoints.isEmpty())
				pw.println(artPoints.pollFirst());
			
			
		}
		
		pw.flush();
		pw.close();
	}
}
