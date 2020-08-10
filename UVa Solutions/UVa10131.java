import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Arrays;

public class UVa10131 {
	
	static Triplet[] array;
	static int[] parent;
	static Stack<Integer> print;
	
	public static void printLIS(int u) {
		if(parent[u] == u) {
			print.push(array[u].n);
			return;
		}
		
		print.push(array[u].n);
		printLIS(parent[u]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		ArrayList<String> in = new ArrayList<>();
		while(true) {
			if(!br.ready())
				break;
			in.add(br.readLine());
		}
		
		array = new Triplet[in.size()];
		for(int i = 0; i < array.length; i++) {
			st = new StringTokenizer(in.get(i));
			array[i] = new Triplet(i + 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(array);
		
				
		int[] LIS = new int[array.length];
		parent = new int[array.length];
		for(int i = 0; i < parent.length; i++)
			parent[i] = i;
		LIS[0] = 1;
		
		for(int i = 1; i < array.length; i++) {
			int max = 1;
			for(int j = i - 1; j >= 0; j--) {
				if(array[j].w < array[i].w && array[j].s > array[i].s) {
					if(LIS[j] + 1 > max) {
						max = LIS[j] + 1;
						parent[i] = j;
					}
				}
			}
			LIS[i] = max;
		}
		
		int max = 0, maxIndex = 0;
		for(int i = 0; i < LIS.length; i++) {
			if(LIS[i] > max) {
				max = LIS[i];
				maxIndex = i;
			}
		}
		
		print = new Stack<Integer>();
		printLIS(maxIndex);
		System.out.println(print.size());
		while(!print.isEmpty())
			System.out.println(print.pop());
	}
	
	public static class Triplet implements Comparable<Triplet> {
		int n;
		int w;
		int s;
		
		public Triplet(int n, int w, int s) {
			this.n = n;
			this.w = w;
			this.s = s;
		}
		
		public int compareTo(Triplet t) {
			if(this.w == t.w)
				return t.s - this.s;
			return this.w - t.w;
		}
	}
}
