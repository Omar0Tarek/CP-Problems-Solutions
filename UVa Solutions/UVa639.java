import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class UVa639 {
	
	//Most naiive solution, try all positions without pruning, we can check when placing at each position to see if it is valid or not in only 8 steps(looping over its column and row)
	static int n;
	static char[][] grid;
	static int max;
	
	public static void backtrack(int i, int j, int count) {
		if(j == n) {
			if(i == n - 1) {
				if(check())
					max = Math.max(max, count);
				return;
			}
			
			j = 0;
			i++;
		}
		
		if(grid[i][j] == 'X') {
			backtrack(i, j + 1, count);
		} else {
			grid[i][j] = 'B';
			backtrack(i, j + 1, count + 1);
			grid[i][j] = '.';
			backtrack(i, j + 1, count);
		}
	}
	
	public static boolean check() {
		for(int i = 0; i < n; i++) {
			int sum = 0;
			for(int j = 0; j < n; j++) {
				if(grid[i][j] == 'B') {
					if(++sum == 2)
						return false;
				} else if(grid[i][j] == 'X') {
					sum = Math.max(0, sum - 1);
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			int sum = 0;
			for(int j = 0; j < n; j++) {
				if(grid[j][i] == 'B') {
					if(++sum == 2)
						return false;
				} else if(grid[j][i] == 'X') {
					sum = Math.max(0, sum - 1);
				}
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			n = Integer.parseInt(br.readLine());
			if(n == 0)
				break;
			
			grid = new char[n][n];
			for(int i = 0; i < n; i++) {
				String s = br.readLine();
				for(int j = 0; j < n; j++)
					grid[i][j] = s.charAt(j);
			}
			
			max = 0;
			backtrack(0, 0, 0);
			
			System.out.println(max);
		}
	}
}
