import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class UVa884 {
	
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
		
		sieve(2703663);
		while(true) {
			if(!br.ready())
				break;
			
			int n = Integer.parseInt(br.readLine());
			pw.println(pf[n]);
		}
		
		//System.out.println(Arrays.binarySearch(pf, 10000001));
		System.out.println(pf[2703663]);
		
		pw.flush();
		pw.close();
	}
}
