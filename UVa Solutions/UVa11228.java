import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;

public class UVa11228 {
	
	static Edge[] EdgeList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		for(int counter = 1; counter <= t; counter++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken());
			Pair[] array = new Pair[n];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				array[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			EdgeList = new Edge[n * (n - 1) / 2];
			int c = 0;
			for(int i = 0; i < n; i++) {
				for(int j = i + 1; j < n; j++) {
					EdgeList[c++] = new Edge(i, j, Math.sqrt(Math.pow(array[i].x - array[j].x, 2) + Math.pow(array[i].y - array[j].y, 2)));
				}
			}
			
			Arrays.sort(EdgeList);
			int j;
			for(j = 0; j < EdgeList.length; j++) {
				if(EdgeList[j].w >= r)
					break;
			}
			
			DSU tree = new DSU(n);
			
			double city = 0;
			for(int i = 0; i < j; i++) {
				Edge e = EdgeList[i];
				if(tree.union(e.u, e.v)) {
					city += e.w;
				}
			}
			
			int x = tree.sets;
			double state = 0;
			for(int i = j; i < EdgeList.length; i++) {
				Edge e = EdgeList[i];
				if(tree.union(e.u, e.v)) {
					state += e.w;
				}
			}
			
			pw.printf("Case #%d: %d %.0f %.0f\n", counter, x, city, state);
		}
		
		pw.flush();
		pw.close();
	}
	
	public static class Pair {
		int x, y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
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
			return this.w < e.w ? -1 : 1;
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
