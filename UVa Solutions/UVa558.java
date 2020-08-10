import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

public class UVa558 {
	
	static int INF = (int)1e9;
	static int n;
	static ArrayList<Pair>[] adjList;
	static int[] dist;
	
	public static boolean negCycle() {
		dist = new int[n];
		Arrays.fill(dist, INF);
		dist[0] = 0;
		
		for(int i = 0; i < n; i++) {
			boolean stop = false;
			for(int u = 0; u < n; u++) {
				for(Pair v : adjList[u]) {
					if(dist[u] + v.w < dist[v.v]) {
						dist[v.v] = dist[u] + v.w;
						stop = true;
					}
				}
			}
			
			if(stop)
				break;
		}
		
		boolean negCycle = false;
		outer:
		for(int u = 0; u < n; u++) {
			for(Pair v : adjList[u]) {
				if(dist[u] + v.w < dist[v.v]) {
					dist[v.v] = dist[u] + v.w;
					negCycle = true;
					break outer;
				}
			}
		}
		
		
		return negCycle;
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
			
			adjList = new ArrayList[n];
			for(int i = 0; i < n; i++)
				adjList[i] = new ArrayList<Pair>();
			while(m-->0) {
				st = new StringTokenizer(br.readLine());
				adjList[Integer.parseInt(st.nextToken())].add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			pw.println(negCycle() ? "possible" : "not possible");
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
