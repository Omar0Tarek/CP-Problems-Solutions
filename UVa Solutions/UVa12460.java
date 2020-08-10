import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.ArrayList;

public class UVa12460 {
	
	public static int calcDiff(String a, String b) {
		if(a.length() != b.length())
			return 2;
		
		int count = 0;
		for(int i = 0; i < a.length(); i++)
			if(a.charAt(i) != b.charAt(i))
				count++;
		return count;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		ArrayList<String> array = new ArrayList<>();
		TreeMap<String, Integer> map = new TreeMap<>();
		
		while(true) {
			String s = br.readLine();
			if(s.equals("--"))
				break;
			array.add(s);
			map.put(s, array.size() - 1);
		}
		
		DSU set = new DSU(array.size());
		for(int i = 0; i < array.size(); i++)
			for(int j = i + 1; j < array.size(); j++)
				if(calcDiff(array.get(i), array.get(j)) == 1)
					set.union(i, j);
		
		while(true) {
			if(!br.ready())
				break;
			
			st = new StringTokenizer(br.readLine());
			String a = st.nextToken(), b = st.nextToken();
			
			if(map.containsKey(a) && map.containsKey(b))
				pw.println(set.isSameSet(map.get(a), map.get(b)) ? "Yes" : "No");
			else
				pw.println("No");
		}
		
		pw.flush();
		pw.close();
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
