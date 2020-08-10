import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa408 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		while(true) {
			if(!br.ready())
				break;
			
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
			
			boolean[] gen = new boolean[m];
			int counter = 0;
			int x = 0;
			while(true) {
				if(!gen[x]) {
					gen[x] = true;
					counter++;
				} else {
					break;
				}
				x = (x + s) % m;
			}
			
			pw.printf("%10d%10d    %s\n\n", s, m, counter == m ? "Good Choice" : "Bad Choice");
		}
		
		pw.flush();
		pw.close();
	}
}
