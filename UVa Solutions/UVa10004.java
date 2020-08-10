import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class UVa10004 {
	
	static ArrayList<Integer>[] adjList;
	static boolean isBi;
	static int[] color;
	
	public static void bfs(int u) {
		Queue<Integer> qu = new LinkedList<>();
		color[u] = 0;
		qu.add(u);
		
		while(!qu.isEmpty()) {
			int x = qu.poll();
			for(int v : adjList[x])
				if(color[v] == -1) {
					color[v] = 1 - color[x];
					qu.add(v);
				} else {
					if(color[v] != 1 - color[x])
						isBi = false;
				}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0)
				break;
			int e = Integer.parseInt(br.readLine());
			
			adjList = new ArrayList[n];
			for(int i = 0; i < adjList.length; i++)
				adjList[i] = new ArrayList<Integer>();
			
			for(int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				adjList[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
			}
			
			isBi = true;
			color = new int[n];
			Arrays.fill(color, -1);
			bfs(0);
			System.out.println(isBi ? "BICOLORABLE." : "NOT BICOLORABLE.");
		}
	}
}
