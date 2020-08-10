import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

public class UVa10200 {
	
	static ArrayList<Integer> primes;
	static int[] isPrime;
	static int[] prefix;
	
	public static void sieve(int n) {
		primes = new ArrayList<Integer>();
		isPrime = new int[n + 1];
		Arrays.fill(isPrime, 1);
		isPrime[0] = isPrime[1] = 0;
		
		for(int i = 2; i <= n; i++)
			if(isPrime[i] == 1) {
				if(1l * i * i <= n)
					for(int j = i * i; j <= n; j += i)
						isPrime[j] = 0;
				primes.add(i);
			}
	}
	
	public static boolean isPrime(int n) {
		for(int i : primes) {
			if(i * i > n)
				break;
			if(n % i == 0)
				return false;
		}
		return true;
	}
	
	public static void process(int n) {
		prefix = new int[n + 1];
		
		for(int i = 0; i <= n; i++) {
			int x = f(i);
			if(isPrime(x))
				prefix[i] = 1;
		}
		
		for(int i = 1; i <= n; i++)
			prefix[i] += prefix[i - 1];
	}
	
	public static int f(int i) {
		return i * i + i + 41; 
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		sieve((int)Math.sqrt(1000010041));
		process(10000);
		
		
		while(true) {
			if(!br.ready())
				break;
			
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken());
			pw.printf("%.2f\n", 100.0 * ((prefix[r] - (l == 0 ? 0 : prefix[l - 1])) * 1.0 / (r - l + 1)));
			
		}
		
		pw.flush();
		pw.close();
	}
}
