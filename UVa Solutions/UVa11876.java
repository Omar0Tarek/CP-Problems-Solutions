import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class UVa11876 {
	
	static int[] numDiv;
	public static void modified_sieve(int n) {
		numDiv = new int[n + 1];
		Arrays.fill(numDiv, 1);
		
		for(int i = 2; i <= n; i++)
			if(numDiv[i] == 1)
				for(int j = i; j <= n; j += i) {
					int e = 0, k = j;
					while(k % i == 0) {
						k /= i;
						e++;
					}
					numDiv[j] *= e + 1;
				}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		modified_sieve(1000000);
		ArrayList<Integer> sequence = new ArrayList<>();
		sequence.add(1);
		while(true) {
			int x = sequence.get(sequence.size() - 1);
			x = x + numDiv[x];
			if(x >= 1000000)
				break;
			sequence.add(x);
		}
		
		int t = Integer.parseInt(br.readLine());
		for(int i = 1; i <= t; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken());
			int x = Collections.binarySearch(sequence, l);
			if(x < 0)
				x = -(x + 1);
			x--;
			int y = Collections.binarySearch(sequence, r);
			if(y < 0)
				y = -(y + 1) - 1;
			
			pw.printf("Case %d: %d\n", i, y - x);
		}
		
		//System.out.println(sequence);
		pw.flush();
		pw.close();
	}

}
