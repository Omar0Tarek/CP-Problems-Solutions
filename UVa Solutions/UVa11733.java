import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;

public class UVa11733 {
	
	static int n, a;
	static Edge[] EdgeList;
	static DSU tree;
	
	public static int kruskal() {
		Arrays.sort(EdgeList);
		tree = new DSU(n);
		
		int mst = 0;
		for(Edge e : EdgeList) {
			if(e.w >= a)
				break;
			if(tree.union(e.u, e.v))
				mst += e.w;
		}
		
		return mst;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		for(int counter = 1; counter <= t; counter++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			
			EdgeList = new Edge[m];
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				EdgeList[i] = new Edge(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
			}
			
			int mst = kruskal();
			mst += 1l * tree.sets * a;
			
			sb.append("Case #" + counter + ": " + mst + " " + tree.sets + "\n");
		}
		
		pw.print(sb);
		
		pw.flush();
		pw.close();
	}
	
	public static class Edge implements Comparable<Edge> {
		int u, v, w;
		
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
		
		public int compareTo(Edge e) {
			return this.w - e.w;
		}
	}
	
	public static class DSU {
		
		int sets;
		int[] parent, rank;
		
		public DSU(int size) {
			sets = size;
			parent = new int[size];
			rank = new int[size];
			
			for(int i = 0; i < parent.length; i++)
				parent[i] = i;
		}
		
		public int findParent(int u) {
			return parent[u] == u ? u : (parent[u] = findParent(parent[u]));
		}
		
		public boolean isSameSet(int u, int v) {
			return findParent(u) == findParent(v);
		}
		
		public boolean union(int u, int v) {
			int x = findParent(u);
			int y = findParent(v);
			
			if(x == y)
				return false;
			
			if(rank[x] >= rank[y]) {
				parent[y] = x;
				if(rank[x] == rank[y])
					rank[x]++;
			} else {
				parent[x] = y;
			}
			
			sets--;
			
			return true;
		}
		
	}
}
