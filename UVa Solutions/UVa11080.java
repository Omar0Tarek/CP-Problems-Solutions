import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

public class UVa11080 {
	
	static ArrayList<Integer>[] adjList;
	static int[] color;
	static int white, black;
	static boolean isBi;
	
	public static void color(int u) {
		for(int v : adjList[u]) {
			if(color[v] == -1) {
				color[v] = 1 - color[u];
				if(color[v] == 0)
					white++;
				else
					black++;
				color(v);
			} else {
				if(color[v] == color[u])
					isBi = false;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[v];
			for(int i = 0; i < v; i++)
				adjList[i] = new ArrayList<Integer>();
			
			while(e-->0) {
				st=  new StringTokenizer(br.readLine());
				int i = Integer.parseInt(st.nextToken()), j = Integer.parseInt(st.nextToken());
				adjList[i].add(j);
				adjList[j].add(i);
			}
			
			color = new int[v];
			Arrays.fill(color, -1);
			isBi = true;
			
			int sum = 0;
			for(int i = 0; i < color.length; i++)
				if(color[i] == -1) {
					color[i] = 0;
					white = 1; black = 0;
					color(i);
					if(white == 1 && black == 0)
						sum += 1;
					else
						sum += Math.min(white,  black);
				}
			
			System.out.println(isBi ? sum : -1);
		}
	}
}
