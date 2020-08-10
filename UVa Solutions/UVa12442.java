import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;

public class UVa12442 {
	
	static ArrayList<Integer>[] adjList;
	static boolean[] vis, vis2;
	
	public static int dfs(int u) {
		vis[u] = true;
		vis2[u] = true;
		
		int sum = 0;
		for(int v : adjList[u])
			if(!vis[v]) {
				sum += 1 + dfs(v);
			}
		
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine()), counter = 1;
		while(t-->0) {
			int n = Integer.parseInt(br.readLine());
			
			int[] in = new int[n];
			adjList = new ArrayList[n];
			for(int i = 0; i < n; i++)
				adjList[i] = new ArrayList<Integer>();
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1, v = Integer.parseInt(st.nextToken()) - 1;
				adjList[u].add(v);
				in[v]++;
			}
			
			vis = new boolean[n];
			vis2 = new boolean[n];
			int max = 0, maxIndex = (int)1e9;
			for(int i = 0; i < n; i++) {
				if(in[i] == 0) {
					vis = new boolean[n];
					int x = 1 + dfs(i);
					if(x > max) {
						max = x;
						maxIndex = i;
					} else if(x == max) {
						if(i < maxIndex)
							maxIndex = i;
					}	
				}
			}
			
			for(int i = 0; i < n; i++)
				if(!vis2[i]) {
					int x = 1 + dfs(i);
					if(x > max) {
						max = x;
						maxIndex = i;
					} else if(x == max) {
						if(i < maxIndex)
							maxIndex = i;
					}	
				}
			
			pw.printf("Case %d: %d\n", counter++, maxIndex + 1);
		}
		
		pw.flush();
		pw.close();
	}
}
