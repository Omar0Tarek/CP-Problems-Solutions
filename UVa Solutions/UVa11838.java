import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Arrays;

public class UVa11838 {
	
	static int n, numSCC, timer;
	static ArrayList<Integer>[] adjList;
	static int[] dfs_low, dfs_num;
	static boolean[] isSCC;
	static Stack<Integer> stack;
	
	public static void SCC() {
		timer = numSCC = 0;
		stack = new Stack<Integer>();
		
		dfs_num = new int[n];
		dfs_low = new int[n];
		isSCC = new boolean[n];
		Arrays.fill(dfs_num, -1);
		
		for(int i = 0; i < n; i++)
			if(dfs_num[i] == -1)
				dfs(i);
		
	}
	
	public static void dfs(int u) {
		dfs_num[u] = dfs_low[u] = timer++;
		stack.add(u);
		
		for(int v : adjList[u]) {
			if(dfs_num[v] == -1)
				dfs(v);
			if(!isSCC[v])
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
		}
		
		if(dfs_low[u] == dfs_num[u]) {
			numSCC++;
			while(true) {
				int x = stack.pop();
				isSCC[x] = true;
				if(x == u)
					break;
			}
		}
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
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1, v = Integer.parseInt(st.nextToken()) - 1, p = Integer.parseInt(st.nextToken());
				adjList[u].add(v);
				if(p == 2)
					adjList[v].add(u);
			}
			
			SCC();
			if(numSCC == 1)
				pw.println(1);
			else
				pw.println(0);
		}
		
		pw.flush();
		pw.close();
	}
}