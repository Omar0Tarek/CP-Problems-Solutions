import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.ArrayList;
import java.util.Collections;

public class UVa10034 {
	
	static int n;
	static ArrayList<Edge> edgeList;
	
	public static double mst() {
		double mst = 0;
		Collections.sort(edgeList);
		UFDS set = new UFDS(n);
		for(Edge e : edgeList) {
			if(set.isSameSet(e.u, e.v))
				continue;
			mst += e.w;
			set.union(e.u, e.v);
		}
		return mst;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			br.readLine();
			n = Integer.parseInt(br.readLine());
			Pair[] array = new Pair[n];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				array[i] = new Pair(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
			}
			
			edgeList = new ArrayList<>();
			for(int i = 0; i < n; i++)
				for(int j = i + 1; j < n; j++)
					edgeList.add(new Edge(i, j, Math.sqrt(Math.pow(array[i].x - array[j].x, 2) + Math.pow(array[i].y - array[j].y, 2))));
			
			System.out.printf("%.2f\n", mst());
			System.out.print(t == 0 ? "" : "\n");
		}
	}
	
	public static class Pair {
		double x;
		double y;
		
		public Pair(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static class Edge implements Comparable<Edge> {
		int u;
		int v;
		double w;
		
		public Edge(int u, int v, double w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
		
		public int compareTo(Edge e) {
			if(this.w == e.w) {
				if(this.u == e.u)
					return this.v - e.v;
				return this.u - e.u;
			}
			return this.w > e.w ? 1 : -1;
		}
	}
	

	public static class UFDS {
	
		int[] parent, rank, setSize;
		int sets;
		
		public UFDS(int sets) {
			this.sets = sets;
			
			parent = new int[sets];
			rank = new int[sets];
			setSize = new int[sets];
			
			for(int i = 0; i < sets; i++) {
				parent[i] = i;
				setSize[i] = 1;
			}
		}
		
		public int findSet(int u) {
			return parent[u] == u ? u : (parent[u] = findSet(parent[u]));
		}
		
		public boolean isSameSet(int u, int v) {
			return findSet(u) == findSet(v);
		}
		
		public void union(int u, int v) {
			int x = findSet(u);
			int y = findSet(v);
			
			if(x != y) {
				if(rank[x] >= rank[y]) {
					parent[y] = x;
					if(rank[x] == rank[y])
						rank[x]++;
					setSize[x] += setSize[y];
				} else {
					parent[x] = y;
					setSize[y] += setSize[x];
				}
				
				sets--;
			}
		}
		
		public int getSets() {
			return this.sets;
		}
		
		public int getSetSize(int u) {
			return setSize[findSet(u)];
		}
	}
}
