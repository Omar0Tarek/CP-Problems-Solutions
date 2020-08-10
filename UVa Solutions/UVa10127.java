import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
// Solution not working, it will be solved with math modulus tricks
public class UVa10127 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		while(true) {
			if(!br.ready())
				break;
			
			int n = Integer.parseInt(br.readLine());
			String s = "";
			while(true) {
				s += "1";
				BigInteger b = new BigInteger(s);
				if(b.remainder(new BigInteger(n + "")).equals(new BigInteger("0"))) {
					pw.println(s.length());
					break;
				}
			}
		}
		
		pw.flush();
		pw.close();
	}
}
