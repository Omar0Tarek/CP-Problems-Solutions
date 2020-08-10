import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

public class UVa516 {
	
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
	
	static ArrayList<Integer> factors;
	public static void factorize(int n) {
		factors = new ArrayList<Integer>();
		
		for(int i = 0; i < primes.size(); i++) {
			if(n == 1)
				break;
			
			int exp = 0;
			while(n % primes.get(i) == 0) {
				n /= primes.get(i);
				exp++;
			}
			
			if(exp > 0) {
				factors.add(primes.get(i));
				factors.add(exp);
			}
		}
		
		if(n > 1) {
			factors.add(n);
			factors.add(1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		sieve(32767);
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			if(st.countTokens() == 1)
				break;
			
			int n = 1;
			while(st.hasMoreTokens())
				n *= (int)Math.pow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			factorize(n - 1);
			
			for(int i = factors.size() - 1; i >= 0; i -= 2)
				pw.print(factors.get(i - 1) + " " + factors.get(i) + (i == 1 ? "\n" : " "));
		}
		
		pw.flush();
		pw.close();
	}
}
