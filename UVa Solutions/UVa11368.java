import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class UVa11368 {
	// Not working because you have to implement your own binary search
	static Doll[] array;
	
	public static int LIS() {
		ArrayList<Doll> LIS = new ArrayList<>();
		for(int i = 0, m = array.length; i < m; i++) {
			Doll d = array[i];
			int j = Collections.binarySearch(LIS, d);
			if(j < 0)
				j = -(j + 1);
			else if(j >= 0)
				j++;
			
			if(j == LIS.size())
				LIS.add(d);
			else
				LIS.set(j, d);
		}
		
		return LIS.size();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			int n = Integer.parseInt(br.readLine());
			
			array = new Doll[n];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++)
				array[i] = new Doll(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			Arrays.sort(array);
			
			pw.println(LIS());
		}
		
		pw.flush();
		pw.close();
	}
	
	public static class Doll implements Comparable<Doll> {
		int w, h;
		
		public Doll(int w, int h) {
			this.w = w;
			this.h = h;
		}
		
		public int compareTo(Doll d) {
			if(this.w < d.w && this.h < d.h)
				return 1;
			if(this.w == d.w && this.h == d.h)
				return 0;
			else
				return -1;
		}
		
		public boolean equals(Object o) {
			Doll d = (Doll) o;
			return this.w == d.w && this.h == d.h;
		}
	}
}
