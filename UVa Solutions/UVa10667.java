import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa10667 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int INF = (int)1e9;
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			int s = Integer.parseInt(br.readLine());
			long[][] grid = new long[s][s];
			for(int i = 0; i < grid.length; i++)
				Arrays.fill(grid[i], 1);
			
			int n = Integer.parseInt(br.readLine());
			while(n-->0) {
				st = new StringTokenizer(br.readLine());
				int i = Integer.parseInt(st.nextToken()) - 1, j = Integer.parseInt(st.nextToken()) - 1, k = Integer.parseInt(st.nextToken()) - 1, l = Integer.parseInt(st.nextToken()) - 1;
				for(; i <= k; i++) {
					for(int x = j; x <= l; x++)
						grid[i][x] = -INF;
				}
			}
			
			for(int i = 0; i < s; i++)
				for(int j = 1; j < s; j++)
					grid[i][j] += grid[i][j - 1];
			
			for(int i = 0; i < s; i++)
				for(int j = 1; j < s; j++)
					grid[j][i] += grid[j - 1][i];
			
			long max = 0;
			for(int i = 0; i < s; i++) {
				for(int j = 0; j < s; j++) {
					for(int k = i; k < s; k++) {
						for(int l = j; l < s; l++) {
							long sum = grid[k][l];
							if(i > 0) sum -= grid[i - 1][l];
							if(j > 0) sum -= grid[k][j - 1];
							if(i > 0 && j > 0) sum += grid[i - 1][j - 1];
							max = Math.max(max, sum);
						}
					}
				}
			}
			
			System.out.println(max);
		}
		
	}
}
