import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa926 {
	
	static int n;
	static int xf, yf;
	static boolean[][][] fix;
	static long[][] memo;
	
	public static long dp(int x, int y) {
		if(x == xf && y == yf)
			return 1;
		if(x > xf || y > yf)
			return 0;
		if(memo[x][y] != -1)
			return memo[x][y];
		
		long sum = 0;
		if(!fix[x][y][0])
			sum += dp(x + 1, y);
		if(!fix[x][y][1])
			sum += dp(x, y + 1);
		return memo[x][y] = sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		int c = Integer.parseInt(br.readLine());
		while(c-->0) {
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int xs = Integer.parseInt(st.nextToken()) - 1, ys = Integer.parseInt(st.nextToken()) - 1;
			st = new StringTokenizer(br.readLine());
			xf = Integer.parseInt(st.nextToken()) - 1;
			yf = Integer.parseInt(st.nextToken()) - 1;
			
			fix = new boolean[n][n][2];
			int w = Integer.parseInt(br.readLine());
			while(w-->0) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()) - 1, y = Integer.parseInt(st.nextToken()) - 1;
				char d = st.nextToken().charAt(0);
				switch(d) {
					case 'N':
						fix[x][y][1] = true;
						break;
					case 'E':
						fix[x][y][0] = true;
						break;
					case 'S':
						if(y > 0)
							fix[x][y - 1][1] = true;
						break;
					case 'W':
						if(x > 0)
							fix[x - 1][y][0] = true;
						break;
				}
			}
			
			for(int i = 0; i < n; i++)
				fix[n - 1][i][0] = fix[i][n - 1][1] = true;
			
			memo = new long[n][n];
			for(int i = 0; i < n; i++)
				Arrays.fill(memo[i], -1);
			
			pw.println(dp(xs, ys));
		}
		
		pw.flush();
		pw.close();
	}
}
