import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class UVa10360 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			int d = Integer.parseInt(br.readLine()), n = Integer.parseInt(br.readLine());
			int[][] sum = new int[1025][1025];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken()), p = Integer.parseInt(st.nextToken());
				
				for(int j = x - d; j <= x + d; j++) {
					for(int k = y - d; k <= y + d; k++) {
						if(j < 0 || j > 1024 || k < 0 || k > 1024)
							continue;
						sum[j][k] += p;
					}
				}
				
			}
			
			int maxP = 0, maxX = 0, maxY = 0;
			
			for(int i = 0; i < 1025; i++)
				for(int j = 0; j < 1025; j++)
					if(sum[i][j] > maxP) {
						maxP = sum[i][j];
						maxX = i;
						maxY = j;
					}
			
			System.out.printf("%d %d %d\n", maxX, maxY, maxP);
		}
	}
}

//NOT Working Solution
/*
public class UVa10360 {
	

	static int[][] grid;
	static int max, count, d;
	
	static int i = 0, j = 0;
	static int maxX, maxY, minX, minY;
	
	static boolean[][] vis;
	static int[] dx = {1, -1, 0, 0};//, 1, 1, -1, -1};
	static int[] dy = {0, 0, 1, -1};//, 1, -1, 1, -1};
	
	public static void floodfill(int x, int y) {
		vis[x][y] = true;
		count += grid[x][y];
		for(int i = 0; i < dx.length; i++)
			if(isValid(x + dx[i], y + dy[i]))
				floodfill(x + dx[i], y + dy[i]);
	}
	
	public static boolean isValid(int x, int y) {
		return x >= minX && x <= maxX && y <= maxY && y >= minY && Math.abs(x - i) <= d && Math.abs(y - j) <= d && !vis[x][y];
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		while(t-->0) {
			d = sc.nextInt();
			grid = new int[1025][1025];
			
			int n = sc.nextInt();
			while(n-->0) {
				int x = sc.nextInt(), y = sc.nextInt();
				maxX = Math.max(x, maxX);
				maxY = Math.max(y, maxY);
				minX = Math.min(x, minX);
				minY = Math.min(y, minY);
				grid[x][y] = sc.nextInt();
			}
			
			max = 0;
			int printX = 0, printY = 0;
			for(i = minX; i < maxX; i++)
				for(j = minY; j < maxY; j++) {
					vis = new boolean[1025][1025];
					count = 0;
					floodfill(i, j);
					if(count > max) {	
						max = count;
						printX = i;
						printY = j;
					}
				}
			
			System.out.println(printX + " " + printY + " " + max);
		}
	}
}
*/