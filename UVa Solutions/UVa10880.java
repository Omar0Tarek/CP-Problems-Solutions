import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class UVa10880 {
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		int counter = 1;
		while(t-->0) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken());
			int x = c - r;
			
			pw.printf("Case #%d:", counter++);
			if(x == 0)
				pw.println(" 0");
			else {
				ArrayList<Integer> div = new ArrayList<>();
				for(int i = 1; i * i <= x; i++)
					if(x % i == 0) {
						if(i > r)
							div.add(i);
						if(x / i > r && i != x / i)
							div.add(x / i);
					}
				Collections.sort(div);
				for(int i : div)
					pw.print(" " + i);
				pw.print("\n");
			}
		}
		
		pw.flush();
		pw.close();
	}

}
