import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.math.BigInteger;

public class UVa10925 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int counter = 1;
		while(true) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()), f = Integer.parseInt(st.nextToken());
			if(n + f == 0)
				break;
			
			BigInteger sum = new BigInteger("0");
			while(n-->0)
				sum = sum.add(new BigInteger(br.readLine()));
			
			System.out.printf("Bill #%d costs %s: each friend should pay %s\n\n", counter++, sum, sum.divide(new BigInteger(Integer.toString(f))));
		}
	}
}
