import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;

public class UVa10986 {
	
	static int INF = (int)1e9;
	static int n;
	static ArrayList<Pair>[] adjList;
	static int[] dist;
	public static void SSSP(int x) {
		dist = new int[n];
		Arrays.fill(dist, INF);
		dist[x] = 0;
		
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		pq.add(new Pair(x, 0));
		while(!pq.isEmpty()) {
			Pair u = pq.poll();
			if(u.w > dist[u.v])
				continue;
			for(Pair v : adjList[u.v]) {
				if(dist[v.v] > u.w + v.w) {
					dist[v.v] = u.w + v.w;
					pq.add(new Pair(v.v, dist[v.v]));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int counter = 1; counter <= T; counter++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken()), s = Integer.parseInt(st.nextToken()), t = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[n];
			for(int i = 0; i < n; i++)
				adjList[i] = new ArrayList<Pair>();
			
			while(m-->0) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken());
				adjList[u].add(new Pair(v, w));
				adjList[v].add(new Pair(u, w));
			}
			
			SSSP(s);
			pw.printf("Case #%d: %s\n", counter, dist[t] == INF ? "unreachable" : (dist[t] + ""));
		}
		
		pw.flush();
		pw.close();
	}
	
	public static class Pair implements Comparable<Pair> {
		int v, w;
		
		public Pair(int v, int w) {
			this.v = v;
			this.w = w;
		}
		
		public int compareTo(Pair p) {
			return this.w - p.w;
		}
	}
}
