import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

public class UVa11388 {
	
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
	
	public static ArrayList<Pair> factorize(int n) {
		ArrayList<Pair> factors = new ArrayList<Pair>();
		
		for(int i = 0; i < primes.size(); i++) {
			if(n == 1)
				break;
			int exp = 0;
			while(n % primes.get(i) == 0) {
				n /= primes.get(i);
				exp++;
			}
			factors.add(new Pair(primes.get(i), exp));
		}
		
		if(n > 1)
			factors.add(new Pair(n, 1));
		
		return factors;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		sieve((int)Math.sqrt(Math.pow(2, 31)));
		
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			st = new StringTokenizer(br.readLine());
			int gcd = Integer.parseInt(st.nextToken()), lcm = Integer.parseInt(st.nextToken());
			
			ArrayList<Pair> g = factorize(gcd), l = factorize(lcm);
			boolean flag = true;
			if(g.size() > l.size())
				flag = false;
			
			if(flag) {
				for(int i = 0; i < g.size(); i++) {
					if(g.get(i).n != l.get(i).n || l.get(i).f < g.get(i).f) {
						flag = false;
						break;
					}
				}
			}
			
			if(flag || gcd == 1) {
				pw.printf("%d %d\n", gcd, lcm);
			} else {
				pw.printf("-1\n");
			}
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
