import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.Arrays;

public class UVa1235 {
	
	static int n;
	static Edge[] EdgeList;
	
	public static int MST() {
		Arrays.sort(EdgeList);
		DSU tree = new DSU(n);
		int mst = 0;
		
		for(Edge e : EdgeList)
			if(tree.union(e.u, e.v))
				mst += e.w;
		
		return mst;
	}
	
	public static int calcDistance(String a, String b) {
		int diff = 0;
		for(int i = 0; i < a.length(); i++) {
			int x = Math.abs(a.charAt(i) - b.charAt(i));
			diff += Math.min(x, 10 - x);
		}
		
		return diff;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()) + 1;
			String[] array = new String[n];
			array[0] = "0000";
			for(int i = 1; i < n; i++)
				array[i] = st.nextToken();
			
			EdgeList = new Edge[n * (n - 1) / 2];
			int c = 0;
			for(int i = 0; i < n; i++)
				for(int j = i + 1; j < n; j++)
					EdgeList[c++] = new Edge(i, j, calcDistance(array[i], array[j]));
			
			pw.println(MST());
		}
		
		//System.out.println(calcDistance("0100", "9999"));
		
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
