import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Arrays;

public class UVa543 {
	
	static ArrayList<Integer> primes;
	static boolean[] isPrime;
	
	public static void sieve(int n) {
		primes = new ArrayList<Integer>();
		isPrime = new boolean[n + 1];
		Arrays.fill(isPrime, true);
		
		for(int i = 2; i <= n; i++)
			if(isPrime[i]) {
				if(1l * i * i <= n)
					for(int j = i * i; j <= n; j += i)
						isPrime[j] = false;
				if(i != 2)
					primes.add(i);
			}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		sieve(1000000 - 1);
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0)
				break;
			
			for(int i : primes) {
				if(isPrime[n - i]) {
					pw.printf("%d = %d + %d\n", n, i, n - i);
					break;
				}
				
			}
		}
		
		pw.flush();
		pw.close();
	}
}
