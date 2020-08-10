import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class UVa10503 {
	
	static int n, m;
	static int right, left;
	static Pair[] pairs;
	static int[] perm;
	static boolean[] taken;
	static boolean isValid;
	
	public static void backtrack(int i) {
		if(i == n) {
			if(right == perm[n - 1])
				isValid = true;
			return;
		}
		
		for(int j = 1; j <= m; j++) {
			if(!taken[j]) {
				if(i == 0) {
					if(pairs[j].u == left || pairs[j].v == left) {
						taken[j] = true;
						
						int x = 0;
						if(pairs[j].u == left)
							x = pairs[j].v;
						else
							x = pairs[j].u;
						perm[i] = x;
						
						backtrack(i + 1);
						taken[j] = false;
					}
				} else if(pairs[j].u == perm[i - 1] || pairs[j].v == perm[i - 1]) {
					taken[j] = true;
					
					int x = 0;
					if(pairs[j].u == perm[i - 1])
						x = pairs[j].v;
					else
						x = pairs[j].u;
					perm[i] = x;
					
					backtrack(i + 1);
					taken[j] = false;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			n = Integer.parseInt(br.readLine());
			if(n == 0)
				break;
			
			m = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			left = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			right = Integer.parseInt(st.nextToken());
			st.nextToken();
			
			pairs = new Pair[m + 1];
			for(int i = 1; i <= m; i++) {
				st = new StringTokenizer(br.readLine());
				pairs[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			perm = new int[n];
			taken = new boolean[m + 1];
			isValid = false;
			
			backtrack(0);
			System.out.println(isValid ? "YES" : "NO");
		}
	}
	
	public static class Pair {
		int u;
		int v;
		
		public Pair(int u, int v) {
			this.u = u;
			this.v = v;
		}
	}
}
