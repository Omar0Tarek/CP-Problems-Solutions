import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Stack;

public class UVa10459 {
	
	static int n;
	static ArrayList<Integer>[] adjList;
	static TreeSet<Pair> set;
	
	static int max, level[];
	static boolean[] vis;
	
	public static void dfs(int u) {
		vis[u] = true;
		
		for(int v : adjList[u])
			if(!vis[v]) {
				level[v] = level[u] + 1;
				max = Math.max(max, level[v]);
				dfs(v);
			}
	}
	
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		
		while(true) {
			if(!sc.ready())
				break;
			
			n = sc.nextInt();
			
			adjList = new ArrayList[n];
			for(int i = 0; i < n; i++)
				adjList[i] = new ArrayList<Integer>();
			for(int u = 0; u < n; u++) {
				int m = sc.nextInt();
				while(m-->0) {
					int v = sc.nextInt() - 1;
					adjList[u].add(v);
					adjList[v].add(u);
				}
			}
			
			set = new TreeSet<Pair>();
			for(int u = 0; u < n; u++) {
				max = 0;
				vis = new boolean[n];
				level = new int[n];
				level[u] = 0;
				
				dfs(u);
				set.add(new Pair(u + 1, max));
			}
			
			
			pw.print("Best Roots  :");
			
			Pair first = set.pollFirst();
			pw.print(" " + first.u);
			int h = first.h;
			while(true) {
				if(set.isEmpty())
					break;
				Pair p = set.pollFirst();
				if(p.h != h) {
					set.add(p);
					break;
				}
				pw.print(" " + p.u);
			}
			
			pw.print("\n");
			pw.print("Worst Roots :");
			
			Stack<Integer> nodes = new Stack<>();
			Pair last = set.pollLast();
			nodes.push(last.u);
			h = last.h;
			while(true) {
				if(set.isEmpty())
					break;
				Pair p = set.pollLast();
				if(p.h != h) {
					set.add(p);
					break;
				}
				nodes.push(p.u);
			}
			
			while(!nodes.isEmpty())
				pw.print(" " + nodes.pop());
			pw.print("\n");
		}
		
		pw.flush();
		pw.close();
	}
	
	public static class Pair implements Comparable<Pair> {
		int u, h;
		
		public Pair(int u, int h) {
			this.u = u;
			this.h = h;
		}
		
		public int compareTo(Pair p) {
			if(this.h == p.h)
				return this.u - p.u;
			return this.h - p.h;
		}
	}
	
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
		
		public double nextDouble() throws IOException
		{
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if(x.charAt(0) == '-')
			{
				neg = true;
				start++;
			}
			for(int i = start; i < x.length(); i++)
				if(x.charAt(i) == '.')
				{
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				}
				else
				{
					sb.append(x.charAt(i));
					if(dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg?-1:1);
		}
		
		public boolean ready() throws IOException {return br.ready();}


	}
}