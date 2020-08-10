import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;

public class UVa1112 {
	
	static int INF = (int)1e9;
	static int n;
	static ArrayList<Pair>[] adjList;
	static int[] dist;
	public static void dijkstra(int source) {
		dist = new int[n];
		Arrays.fill(dist, INF);
		dist[source] = 0;
		
		PriorityQueue<Pair> qu = new PriorityQueue<>();
		qu.add(new Pair(source, 0));
		while(!qu.isEmpty()) {
			Pair u = qu.poll();
			if(dist[u.v] < u.w)
				continue;
			for(Pair v : adjList[u.v]) {
				if(dist[v.v] > u.w + v.w) {
					dist[v.v] = u.w + v.w;
					qu.add(new Pair(v.v, dist[v.v]));
				}
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			br.readLine();
			n = Integer.parseInt(br.readLine());
			int source = Integer.parseInt(br.readLine()) - 1, time = Integer.parseInt(br.readLine());
			
			adjList = new ArrayList[n];
			for(int i = 0; i < n; i++)
				adjList[i] = new ArrayList<Pair>();
			
			int m = Integer.parseInt(br.readLine());
			while(m-->0) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1;
				adjList[Integer.parseInt(st.nextToken()) - 1].add(new Pair(u, Integer.parseInt(st.nextToken())));
			}
			
			dijkstra(source);
			int c = 0;
			for(int i : dist)
				if(i <= time)
					c++;
			
			pw.println(c);
			pw.print(t == 0 ? "" : "\n");
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
