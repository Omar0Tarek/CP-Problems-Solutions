import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class UVa10918 {
	
	static long[] memo;
	
	public static long dp(int i) {
		if(i < 0)
			return 0;
		if(i == 0)
			return 1;
		if(memo[i] != -1)
			return memo[i];
		
		long sum = 3l * dp(i - 2);
		for(int j = 4; j <= i; j += 2)
			sum += 2l * dp(i - j);
		
		return memo[i] = sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		memo = new long[31];
		Arrays.fill(memo, -1);
		
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n == -1)
				break;
			
			pw.println(dp(n));
		}
		
		pw.flush();
		pw.close();
	}
}
