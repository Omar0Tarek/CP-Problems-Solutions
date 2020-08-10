import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class UVa908 {
	
	static int n;
	static ArrayList<Edge> EdgeList;
	public static int MST() {
		Collections.sort(EdgeList);
		DSU set = new DSU(n);
		int mst = 0;
		for(Edge e : EdgeList)
			if(set.union(e.u, e.v))
				mst += e.w;
		return mst;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		int counter = 0;
		while(true) {
			if(!br.ready())
				break;
			
			if(counter++ != 0) {
				br.readLine();
				pw.print("\n");
			}
			
			n = Integer.parseInt(br.readLine());
			EdgeList = new ArrayList<>();
			for(int i = 0; i < n - 1; i++) {
				st = new StringTokenizer(br.readLine());
				EdgeList.add(new Edge(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())));
			}
			
			int init = MST();
			
			int k = Integer.parseInt(br.readLine());
			for(int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				EdgeList.add(new Edge(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())));
			}
			
			int end = MST();
			
			int m = Integer.parseInt(br.readLine());
			for(int i = 0; i < m; i++)
				br.readLine();
			
			pw.println(init + "\n" + end);
			
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
