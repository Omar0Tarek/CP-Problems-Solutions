import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa10090 {
	
	public static Tuple gcd(long a, long b) {
		if(a == 0)
			return new Tuple(b, 0, 1);
		
		Tuple t = gcd(b % a, a);
		
		long x = t.x, y = t.y;
		t.x = y - ((b / a) * x);
		t.y = x;
		
		return t;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		while(true) {
			long c = Long.parseLong(br.readLine());
			if(c == 0)
				break;
			
			st =  new StringTokenizer(br.readLine());
			long c1 =  Long.parseLong(st.nextToken()), a =  Long.parseLong(st.nextToken());
			st =  new StringTokenizer(br.readLine());
			long c2 =  Long.parseLong(st.nextToken()), b =  Long.parseLong(st.nextToken());
			
			Tuple t = gcd(a, b);
			long g = t.g, xg = t.x, yg = t.y;
			
			boolean flag = true;
			if(c % g != 0)
				flag = false;
			
			long x = 0, y = 0;
			if(flag) {
				long x0 = c / g * xg, y0 = c / g * yg;
				//pw.println(x0 + " " + y0);
				long u = (int)Math.ceil(-x0 * g * 1.0 / b), v = (int)Math.floor(y0 * g * 1.0 / a);
				
				if(v < u) {
					flag = false;
				} else {
					long x1 = x0 + (u * (int)(b * 1.0 / g)), y1 = y0 - (u * (int)(a * 1.0 / g));
					long x2 = x0 + (v * (int)(b * 1.0 / g)), y2 = y0 - (v * (int)(a * 1.0 / g));
					
					x = x1;
					y = y1;
					if(x1 * c1 + y1 * c2 > x2 * c1 + y2 * c2) {
						x = x2;
						y = y2;
					}
				}
			}
			
			pw.println(!flag ? "failed" : x + " " + y);
		}
		
		pw.flush();
		pw.close();
	}
	
	public static class Tuple {
		long g, x, y;
		
		public Tuple(long g, long x, long y) {
			this.g = g;
			this.x = x;
			this.y = y;
		}
	}
	
	
}
