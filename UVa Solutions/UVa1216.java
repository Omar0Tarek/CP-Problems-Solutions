import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

public class UVa1216 {
	
	static int n, c;
	static Edge[] EdgeList;
	
	public static double MST() {
		Arrays.sort(EdgeList);
		DSU tree = new DSU(n);
		
		double minimax = 0;
		for(Edge e : EdgeList) {
			if(tree.sets == c)
				break;
			if(tree.union(e.u, e.v))
				minimax = Math.max(minimax, e.w);
		}
		
		return minimax;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			c = Integer.parseInt(br.readLine());
			ArrayList<String> in = new ArrayList<>();
			while(true) {
				String s = br.readLine();
				if(s.equals("-1"))
					break;
				in.add(s);
			}
			n = in.size();
			
			int[][] array = new int[n][2];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(in.get(i));
				array[i][0] = Integer.parseInt(st.nextToken());
				array[i][1] = Integer.parseInt(st.nextToken());
			}
			
			EdgeList = new Edge[n * (n - 1) / 2];
			int counter = 0;
			for(int i = 0; i < n; i++) {
				for(int j = i + 1; j < n; j++) {
					EdgeList[counter++] = new Edge(i, j, Math.sqrt(Math.pow(array[i][0] - array[j][0], 2) + Math.pow(array[i][1] - array[j][1], 2)));
				}
			}
			
			pw.println((int)Math.ceil(MST()));
		}
		
		pw.flush();
		pw.close();
	}
	
	public static class Edge implements Comparable<Edge> {
		int u, v;
		double w;
			
		public Edge(int u, int v, double w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
		
		public int compareTo(Edge e) {
			return this.w - e.w < 0 ? -1 : 1;
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
