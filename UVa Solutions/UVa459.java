import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class UVa459 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		br.readLine();
		
		while(t-->0) {
			int max = br.readLine().charAt(0) - 64;
			UFDS set = new UFDS(max);

			while(true) {
				if(!br.ready())
					break;
				String s = br.readLine();
				if(s.length() == 0)
					break;
				
				set.union(s.charAt(0) - 65, s.charAt(1) - 65);
			}
			
			System.out.println(set.sets);
			System.out.print(t == 0 ? "" : "\n");
		}
	}
	
	public static class UFDS {
		int[] parent, rank, size;
		int sets;
		
		public UFDS(int size) {
			this.rank = new int[size];
			this.parent = new int[size];
			this.size = new int[size];
			
			this.sets = size;
			
			for(int i = 0; i < size; i++) {
				this.parent[i] = i;
				this.size[i] = 1;
			}
		}
		
		public int findSet(int i) {
			return parent[i] == i ? i : (parent[i] = findSet(parent[i]));
		}
		
		public boolean isSameSet(int i, int j) {
			return findSet(i) == findSet(j);
		}
		
		public void union(int i, int j) {
			int x = findSet(i);
			int y = findSet(j);
			
			if(x == y)
				return;
			
			if(rank[x] > rank[y]) {
				parent[y] = x;
				size[x] += size[y];
			} else {
				parent[x] = y;
				if(rank[x] == rank[y]) rank[y]++;
				size[y] += size[x];
			}
			sets--;
		}
		
		public int sets() {
			return this.sets;
		}
		public int sizeOfSet(int i) {
			return size[findSet(i)];
		}
	}
	/*
	static ArrayList<Integer>[] array;
	static boolean[] visited;
	
	public static void dfs(int node) {
		visited[node] = true;
		for(int next : array[node]) {
			if(!visited[next])
				dfs(next);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		br.readLine();
		while(n-->0) {
			int max  = br.readLine().charAt(0) - 65;
			array = new ArrayList[max + 1];
			for(int i = 0; i < array.length; i++)
				array[i] = new ArrayList<Integer>();
			
			while(br.ready()) {
				String enter = br.readLine();
				if(enter.length() == 0)
					break;
				
				int a = enter.charAt(0) - 65, b = enter.charAt(1) - 65;
				array[a].add(b);
				array[b].add(a);
			}
			
			visited = new boolean[max + 1];
			int count = 0;
			for(int i = 0; i <= max; i++) {
				if(!visited[i]) {
					count++;
					dfs(i);
				}
			}
			
			System.out.println(count);
			System.out.print(n == 0 ? "" : "\n");
		}
		
	}
	*/
}