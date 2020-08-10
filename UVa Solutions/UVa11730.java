import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

public class UVa11730 {
	
	static int[] primes = {2, 3, 5, 7};
	static ArrayList<Integer> factors;
	public static void factorize(int n) {
		factors = new ArrayList<Integer>();
		for(int i : primes) {
			if(n == 1)
				break;
			int e = 0;
			while(n % i == 0) { n /= i; e++;}
			if(e > 0) {
				factors.add(i);
				factors.add(e);
			}
		}
		
		if(n > 1) {
			factors.add(n);
			factors.add(1);
		}
	}
	
	static int INF = (int)1e9;
	static int[] c, v;
	public static int dp(int i, int r) {
		if(r < 0)
			return INF;
		if(r == 0)
			return 0;
		if(i == -1)
			return INF;
		
		//System.out.println(i + " " + r + " " + c[i] + " " + v[i]) ;
		
		if(v[i] == 0)
			return dp(i - 1, r);
		else {
			int min = INF;
			v[i]--;
			min = Math.min(min, 1 + dp(i, r - c[i]));
			v[i]++;
			min = Math.min(min, dp(i - 1, r));
			return min;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		int counter = 1;
		while(true) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()), t = Integer.parseInt(st.nextToken());
			if(s + t == 0)
				break;
			
			factorize(s);
			int n = factors.size() / 2;
			int r = t - s;
			c = new int[n];
			v = new int[n];
			for(int i = 0; i < n; i++) {
				c[i] = factors.get(2 * i);
				v[i] = factors.get(2 * i + 1);
			}
			
			int x = dp(n - 1, r);
			//pw.println(Arrays.toString(c) + "\n" + Arrays.toString(v) + " " + r + " " + x);
			pw.printf("Case %d: %d\n", counter++, x >= INF ? -1 : x);
			
		}
		
		pw.flush();
		pw.close();
	}
}
