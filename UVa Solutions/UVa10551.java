import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.math.BigInteger;

public class UVa10551 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			if(st.countTokens() == 1)
				break;
			
			int b = Integer.parseInt(st.nextToken());
			
			BigInteger p = new BigInteger(st.nextToken(), b);
			BigInteger m = new BigInteger(st.nextToken(), b);
			System.out.println(p.mod(m).toString(b));
		}
	}
}
