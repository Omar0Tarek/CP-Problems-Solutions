import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;

public class UVa976 {
	
	static int r, c;
	static int b, s;
	static char mark;
	static char[][] grid;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static boolean[][] vis;
	public static void floodfill(int x, int y) {
		vis[x][y] = true;
		grid[x][y] = mark;
		
		for(int i = 0; i < dx.length; i++) {
			int xn = x + dx[i], yn = y + dy[i];
			if(isValid(xn, yn))
				floodfill(xn, yn);
		}
	}
	
	public static boolean isValid(int x, int y) {
		return x > -1 && x < r && y > -1 && y < c && !vis[x][y] && grid[x][y] == '#';
	}
	
	static int INF = (int)1e9;
	static int[][] memo;
	static int[] d;
	
	public static int dp(int i, int b) {
		if(i >= c)
			if(b == 0)
				return 0;
			else
				return INF;
		if(b == 0)
			return 0;
		
		if(memo[i][b] != -1)
			return memo[i][b];
		
		return memo[i][b] = Math.min(d[i] + dp(i + s + 1, b - 1),  dp(i + 1, b));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		while(true) {
			if(!br.ready())
				break;
			
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			b = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			
			grid = new char[r][c];
			for(int i = 0; i < r; i++) {
				String s = br.readLine();
				for(int j = 0; j < c; j++)
					grid[i][j] = s.charAt(j);
			}
			
			vis = new boolean[r][c];
			mark = '1';
			floodfill(0, 0);
			mark = '2';
			floodfill(r - 1, c - 1);
			
			d = new int[c];
			for(int i = 0; i < c; i++) {
				int d1 = 0, d2 = 0;
				for(int j = 0; j < r; j++) {
					if(grid[j][i] == '1')
						d1 = j;
					if(grid[j][i] == '2') {
						d2 = j;
						break;
					}
				}
				d[i] = d2 - d1 - 1;
			}
			
			
			memo = new int[c][b + 1];
			for(int i = 0; i < c; i++)
				Arrays.fill(memo[i], -1);
			
			pw.println(dp(0, b));
		}
		
		pw.flush();
		pw.close();
	}
}
