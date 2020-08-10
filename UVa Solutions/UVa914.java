import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class UVa914 {
	
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
		
		sieve(1000000);
		
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken());
			
			int i = Collections.binarySearch(primes, l);
			if(i < 0)
				i = -(i + 1);
			int j = Collections.binarySearch(primes, r);
			if(j < 0)
				j = -(j + 1) - 1;
			
			int[] count = new int[500000];
			for(i++; i <= j; i++)
				count[primes.get(i) - primes.get(i - 1)]++;
			
			int max = 0;
			int maxNumber = 0;
			boolean flag = true;
			
			for(int u = 0; u < count.length; u++) {
				if(count[u] >= max) {
					if(count[u] == max)
						flag = false;
					else
						flag = true;
					max = count[u];
					maxNumber = u;
				}
			}
			
			pw.println(flag ? ("The jumping champion is " + maxNumber ) : "No jumping champion");
		}
		
		pw.flush();
		pw.close();
	}
}
