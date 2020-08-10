import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa10673 {
	
	public static Tuple gcd(int a, int b) {
		if(a == 0)
			return new Tuple(b, 0, 1);
		
		Tuple t = gcd(b % a, a);
		
		int x = t.x, y = t.y;
		t.x = y - ((b / a) * x);
		t.y = x;
		
		return t;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
			int a = x / k, b = (x + k - 1) / k;
			
			Tuple c = gcd(a, b);
			int g = c.g, xg = c.x, yg = c.y;
			
			int p = xg * x / g, q = yg * x / g;
			pw.printf("%d %d\n", p, q);
		}
		
		pw.flush();
		pw.close();
	}
	
	public static class Tuple {
		int g, x, y;
		
		public Tuple(int g, int x, int y) {
			this.g = g;
			this.x = x;
			this.y = y;
		}
	}

}
