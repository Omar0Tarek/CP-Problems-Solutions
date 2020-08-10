import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;

public class UVa10816 {
	
	static int INF = (int)1e9;
	static int n;
	static ArrayList<Road>[] adjList;
	static double[] dist, temp;
	static int[] parent;
	public static void SSSP(int x) {
		dist = new double[n];
		temp = new double[n];
		parent = new int[n];
		Arrays.fill(dist, INF);
		Arrays.fill(temp, INF);
		Arrays.fill(parent, -1);
		dist[x] = temp[x] = 0;
		
		PriorityQueue<Road> pq = new PriorityQueue<>();
		pq.add(new Road(x, 0, 0));
		while(!pq.isEmpty()) {
			Road u = pq.poll();
			if(temp[u.v] < u.t)
				continue;
			if(temp[u.v] == u.t && dist[u.v] < u.l)
				continue;
			
			for(Road v : adjList[u.v]) {
				if(temp[v.v] > Math.max(u.t, v.t)) {
					temp[v.v] = Math.max(u.t, v.t);
					dist[v.v] = u.l + v.l;
					parent[v.v] = u.v;
					pq.add(new Road(v.v, dist[v.v], temp[v.v]));
				} else if(temp[v.v] == Math.max(u.t, v.t)) {
					if(dist[v.v] > u.l + v.l) {
						dist[v.v] = u.l + v.l;
						parent[v.v] = u.v;
						pq.add(new Road(v.v, dist[v.v], temp[v.v]));
					}
				}
			}
		}
	}
	
	public static String find_parent(int u) {
		return parent[u] == -1 ? ((u + 1) + "") : (find_parent(parent[u]) + " " + (u + 1));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		while(true) {
			if(!br.ready())
				break;
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1, t = Integer.parseInt(st.nextToken()) - 1;
			
			adjList = new ArrayList[n];
			for(int i = 0; i < n; i++)
				adjList[i] = new ArrayList<Road>();
			
			while(m-->0) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1, v = Integer.parseInt(st.nextToken()) - 1;
				double l = Double.parseDouble(st.nextToken()), c = Double.parseDouble(st.nextToken());
				
				adjList[u].add(new Road(v, c, l));
				adjList[v].add(new Road(u, c, l));
			}
			
			SSSP(s);
			pw.println(find_parent(t));
			pw.printf("%.1f %.1f\n", dist[t], temp[t]);
		}
		
		pw.flush();
		pw.close();
	}
	
	public static class Road implements Comparable<Road> {
		
		static int EPS = (int)1e-9;
		int v;
		double l, t;
		
		public Road(int v, double l, double t) {
			this.v = v;
			this.l = l;
			this.t = t;
		}
		
		public int compareTo(Road r) {
			if(Math.abs(this.t - r.t) < EPS)
				return this.l - r.l > 0 ? 1 : -1;
			return this.t - r.t > 0 ? 1 : -1;
		}
	}
}
