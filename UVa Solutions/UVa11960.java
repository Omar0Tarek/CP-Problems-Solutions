import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Arrays;

public class UVa11960 {
		
	static int[] isPrime;
	static int[] pre;
	
	public static void modified_sieve(int n) {
		isPrime = new int[n + 1];
		Arrays.fill(isPrime, 1);
		isPrime[0] = 0;
		
		for(int i = 2; i <= n; i++) {
			if(isPrime[i] == 1) {
				for(int j = i; j <= n; j += i) {
					int e = 0, k = j;
					while(k % i == 0) {
						k /= i;
						e++;
					}
					isPrime[j] *= e + 1;
				}
			}
		}
		
		pre = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			if(isPrime[pre[i - 1]] > isPrime[i])
				pre[i] = pre[i - 1];
			else
				pre[i] = i;
		}
	}
	
	public static int log(int x, int y) {
		return (int)(Math.log10(x) / Math.log10(y));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		modified_sieve((int)1e6);
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			pw.println(pre[Integer.parseInt(br.readLine())]);
		}
		
		pw.flush();
		pw.close();
	}
}
