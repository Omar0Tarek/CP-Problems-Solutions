import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class UVa10742 {
	
	static ArrayList<Integer> primes;
	static boolean[] isPrime;
	
	public static void sieve(int n) {
		primes = new ArrayList<Integer>();
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
		
		sieve(1000000);
		
		int counter = 1;
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0)
				break;
			
			if(n < 5) {
				pw.printf("Case %d: 0\n", counter++);
			} else {
				long sum = 0;
				for(int i = 0; primes.get(i) <= n / 2; i++) {
					int j = Collections.binarySearch(primes, n - primes.get(i));
					if(j < 0)
						j = -(j + 1) - 1;
					if(primes.get(j) == primes.get(i))
						break;
					sum += j - i;
				}
				
				pw.printf("Case %d: %d\n", counter++, sum);
			}
		}
		
		pw.flush();
		pw.close();
	}
}
