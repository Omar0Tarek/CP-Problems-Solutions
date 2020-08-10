import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa332 {
	
	public static long gcd(long n, long m) {
		return n == 0 ? m : gcd(m % n, n);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		int counter = 1;
		while(true) {
			st = new StringTokenizer(br.readLine());
			if(st.countTokens() == 1)
				break;
			
			
			int j = Integer.parseInt(st.nextToken());
			String s = st.nextToken();
			int k = s.substring(2).length() - j;
			
			long p = 0, q = 0;
			if(j > 0) {
				p = Integer.parseInt(s.substring(2));
				if(k > 0)
					p -= Integer.parseInt(k == 0 ? "0" : (s + "").substring(2, 2 + k));
				q = (int)(Math.pow(10, k + j) - Math.pow(10, k));
				
				long g = gcd(p, q);
				p /= g;
				q /= g;
			} else {
				p = Integer.parseInt(s.substring(2));
				q = (int)Math.pow(10, k);
						
				long g = gcd(p, q);
				p /= g;
				q /= g;
			}
			
			pw.printf("Case %d: %d/%d\n", counter++, p, q);
		}
		
		pw.flush();
		pw.close();
	}
}
