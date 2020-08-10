import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa10856 {
	
	static int[] pf;
	public static void sieve(int n) {
		pf = new int[n + 1];
		
		for(int i = 2; i <= n; i++) {
			if(pf[i] == 0) {
				for(int j = i; j <= n; j += i) {
					int e = 0, k = j;
					while(k % i == 0) {
						k /= i;
						e++;
					}
					pf[j] += e;
				}
				pf[i] = 1;
			}
		}
		
		for(int i = 3; i <= n; i++)
			pf[i] += pf[i - 1];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		sieve(2703663);
		
		int counter = 1;
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n < 0)
				break;
			
			int x = Arrays.binarySearch(pf, n);
			pw.printf("Case %d: %s\n", counter++, x < 0 ? "Not possible." : (x + "!"));
		}
		
		pw.flush();
		pw.close();
	}
}
