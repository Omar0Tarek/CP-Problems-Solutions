import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

public class UVa10892 {
	
	static ArrayList<Integer> primes;
	static boolean[] isPrime;
	
	public static void sieve(int n) {
		primes = new ArrayList<>();
		isPrime = new boolean[n + 1];
		
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		
		for(int i = 2; i <= n; i++)
			if(isPrime[i]) {
				if(1l * i * i <= n)
					for(int j = i * i; j <= n; j += i)
						isPrime[j] = false;
				
				primes.add(i);
			}
	}
	
	public static long factorize(int n) {
		
		long sum = 1, sum2 = 1;
		for(int i = 0; i < primes.size(); i++) {
			if(n == 1)
				break;
			int exp = 0;
			while(n % primes.get(i) == 0) {
				n /= primes.get(i);
				exp++;
			}
			
			if(exp > 0) {
				sum *= exp + 1;
				sum2 *= exp;
			}
		}
		
		if(n > 1) {
			sum *= 2;
		}
		
		return sum + (sum2 == 1 ? 0 : sum2);
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		sieve((int)Math.sqrt(2e9));
		
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0)
				break;
			
			pw.println(factorize(n));
		}
		
		pw.flush();
		pw.close();
	}
	
	public static class Pair {
		int n, f;
		
		public Pair(int n, int f) {
			this.n = n;
			this.f = f;
		}
	}
}
