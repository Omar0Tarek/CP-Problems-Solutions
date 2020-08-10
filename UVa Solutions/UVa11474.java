import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class UVa11474 {
	
	static int k, d;
	
	public static boolean check(Pair i, Pair j, boolean doc) {
		return Math.sqrt(Math.pow(i.x * 1.0 - j.x * 1.0, 2) + Math.pow(i.y * 1.0 - j.y * 1.0, 2)) <= (doc ? d : k);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			
			ArrayList<Pair> doctor = new ArrayList<>();
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				doctor.add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			ArrayList<Pair>[] tree = new ArrayList[n];
			for(int i = 0; i < tree.length; i++)
				tree[i] = new ArrayList<Pair>();
			for(int i = 0; i < n; i++) {
				int k = Integer.parseInt(br.readLine());
				while(k-->0) {
					st = new StringTokenizer(br.readLine());
					tree[i].add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
				}
			}
			
			DSU set = new DSU(n + m);
			
			for(int i = 0; i < n; i++) {
				for(Pair pi : tree[i]) {
					two: for(int j = 0; j < n; j++) {
						if(set.isSameSet(i, j))
							continue two;
						for(Pair pj : tree[j]) {
							if(check(pi, pj, false)) {
								set.union(i, j);
								continue two;
							}
						}
					}
				}
			}
			
			for(int i = 0; i < m; i++) {
				one: for(int j = 0; j < n; j++) {
					if(set.isSameSet(n + i, j))
						continue one;
					for(Pair p : tree[j]) {
						if(check(doctor.get(i), p, true)) {
							set.union(i + n,  j);
							continue one;
						}
					}
				}
			}
			
			
			boolean flag = false;
			for(int i = 0; i < m; i++)
				if(set.isSameSet(0, n + i)) {
					flag = true;
					break;
				}
			
			System.out.println(flag ? "Tree can be saved :)" :"Tree can't be saved :(");
		}
	}
	
	public static class Pair {
		int x, y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
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
		
		public void union(int u, int v) {
			int x = findParent(u);
			int y = findParent(v);
			
			if(x == y)
				return;
			
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
		}
		
		public int getSetSize(int u) {
			return setSize[findParent(u)];
		}
		
		public int getSets() {
			return sets;
		}
	}
}