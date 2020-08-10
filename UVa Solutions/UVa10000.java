import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class UVa10000 {
	
	static int INF = (int)1e9;
	static int n;
	static ArrayList<Integer>[] adjList;
	static Stack<Integer> stack;
	static boolean[] vis;
	
	public static void toposort(int u) {
		vis[u] = true;
		
		for(int v : adjList[u])
			if(!vis[v])
				toposort(v);
		
		stack.push(u);
	}
	
	static int[] sort;
	static int[] dist;
	public static void SSSP(int s) {
		dist = new int[n];
		Arrays.fill(dist, -INF);
		dist[s] = 0;
		
		for(int i = 0; i < n; i++) {
			int u = sort[i];
			for(int v : adjList[u])
				dist[v] = Math.max(dist[v], dist[u] + 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		
		int counter = 1;
		while(true) {
			n = sc.nextInt();
			if(n == 0)
				break;
			int s = sc.nextInt() - 1;
			
			adjList = new ArrayList[n];
			for(int i = 0; i < n; i++)
				adjList[i] = new ArrayList<Integer>();
			
			while(true) {
				int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
				if(u == -1)
					break;
				adjList[u].add(v);
			}
			
			stack = new Stack<Integer>();
			vis = new boolean[n];
			toposort(s);
			
			sort = new int[n];
			int c = 0;
			while(!stack.isEmpty())
				sort[c++] = stack.pop();
			
			SSSP(s);
			
			int max = 0, min = s + 1;
			for(int i = 0; i < n; i++) {
				if(dist[i] > max) {
					max = dist[i];
					min = i + 1;
				} else if(dist[i] == max) {
					min = Math.min(min, i + 1);
				}
			}
			
			pw.printf("Case %d: The longest path from %d has length %d, finishing at %d.\n\n", counter++, s + 1, max, min);
		}
		
		pw.flush();
		pw.close();
	}
	
	static class Scanner {
		StringTokenizer st;
		BufferedReader br;
 
		public Scanner(InputStream system) {
			br = new BufferedReader(new InputStreamReader(system));
		}
		
		public String next() throws IOException {
			while (st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}
 
		public String nextLine() throws IOException {
			return br.readLine();
		}
 
		public int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
 
		public double nextDouble() throws IOException {
			return Double.parseDouble(next());
		}
 
		public char nextChar() throws IOException {
			return next().charAt(0);
		}
 
		public Long nextLong() throws IOException {
			return Long.parseLong(next());
		}
 
		public boolean ready() throws IOException {
			return br.ready();
		}
 
		public void waitForInput() throws InterruptedException {
			Thread.sleep(3000);
		}
	}
}
