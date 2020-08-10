import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class UVa167 {
	
	static int[][] board;
	static int[] perm;
	static int max;
	static boolean[] rw = new boolean[8], rd = new boolean[15], ld = new boolean[15];
	
	public static void backtrack(int c) {
		if(c == 8) {
			int sum = 0;
			for(int i = 0; i < 8; i++)
				sum += board[perm[i]][i];
			max = Math.max(max, sum);
			return;
		}
		
		for(int r = 0; r < 8; r++) {
			if(!rw[r] && !rd[r + c] && !ld[r - c + 7]) {
				rw[r] = rd[r + c] = ld[r - c + 7] = true;
				perm[c] = r;
				backtrack(c + 1);
				rw[r] = rd[r + c] = ld[r - c + 7] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int k = Integer.parseInt(br.readLine());
		while(k-->0) {
			board = new int[8][8];
			for(int i = 0; i < 8; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 8; j++)
					board[i][j] = Integer.parseInt(st.nextToken());
			}
			
			perm = new int[8];
			max = 0;
			backtrack(0);
			System.out.printf("%5d\n", max);
		}
	}
}
