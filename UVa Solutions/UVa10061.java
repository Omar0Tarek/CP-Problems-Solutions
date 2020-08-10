import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

public class UVa10061 {
	
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
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		sieve((int)Math.sqrt(2e9));
		
		
		
		pw.flush();
		pw.close();
	}
}
