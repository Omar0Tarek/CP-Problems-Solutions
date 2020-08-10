import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.StringTokenizer;
import java.util.Arrays;

public class UVa11321 {

	static int m;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());
			if(n + m == 0) {
				System.out.printf("0 0\n");
				break;
			}
			
			Pair[] array = new Pair[n];
			for(int i = 0; i < n; i++)
				array[i] = new Pair(Integer.parseInt(br.readLine()));
			Arrays.sort(array);
			
			System.out.printf("%d %d\n", n, m);
			for(Pair i : array)
				System.out.println(i.r);
		}
		
	}
	
	public static class Pair implements Comparable<Pair> {
		long r;
		long n;
		
		public Pair(long r) {
			this.r = r;
			this.n = r % m;
		}
		
		public int compareTo(Pair p) {
			
			if(this.n == p.n) {
				long a = this.r, b = p.r;
				
				if((a & 1) != (b & 1)) {
					if((a & 1) == 1)
						return -1;
					else
						return 1;
				} else {
					if((a & 1) == 1)
						return a < b ? 1 : -1;
					else
						return a < b ? -1 : 1;
				}
			}
			
			return this.n < p.n ? -1 : 1;
		}
	}
}
