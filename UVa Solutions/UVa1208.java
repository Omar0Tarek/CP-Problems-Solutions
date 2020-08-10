import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class UVa1208 {
	
	static int n;
	static ArrayList<Edge> EdgeList;
	public static String MST() {
		StringBuilder sb = new StringBuilder("");
		Collections.sort(EdgeList);
		DSU tree = new DSU(n);
		for(Edge e : EdgeList) {
			if(tree.union(e.u, e.v))
				sb.append((char)(e.u + 65) + "-" + (char)(e.v + 65) + " " + e.w + "\n");
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		for(int counter = 1; counter <= t; counter++) {
			n = Integer.parseInt(br.readLine());
			
			EdgeList = new ArrayList<Edge>();
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), ", ");
				for(int j = 0; j < n; j++) {
					int w = Integer.parseInt(st.nextToken());
					if(w != 0)
						EdgeList.add(new Edge(i, j, w));
				}
			}
			
			pw.printf("Case %d:\n", counter);
			pw.print(MST());
		}
		
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
		int[] parent, rank, setSize;
		
		public DSU(int size) {
			sets = size;
			parent = new int[size];
			rank = new int[size];
			setSize = new int[size];
			
			for(int i = 0; i < parent.length; i++)
				parent[i] = i;
			for(int i = 0; i < setSize.length; i++)
				setSize[i] = 1;
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
				setSize[x] += setSize[y];
				if(rank[x] == rank[y])
					rank[x]++;
			} else {
				parent[x] = y;
				setSize[y] += setSize[x];
			}
			
			sets--;
			
			return true;
		}
		
		public int getSetSize(int u) {
			return setSize[findParent(u)];
		}
		
		public int getSets() {
			return sets;
		}
	}
}
