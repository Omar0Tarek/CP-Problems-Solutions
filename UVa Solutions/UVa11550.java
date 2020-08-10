import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class UVa11550 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		main: while(t-->0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
			
			int[][] matrix = new int[n][m];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < m; j++)
					matrix[i][j] = Integer.parseInt(st.nextToken());
			}
			
			boolean[][] adjMatrix = new boolean[n][n];
			
			for(int j = 0; j < m; j++) {
				int count = 0, a = 0, b = 0;
				for(int i = 0; i < n; i++) {
					if(matrix[i][j] == 1) {
						if(count == 0)
							a = i;
						else if(count == 1)
							b = i;
						else {
							System.out.println("No");
							continue main;
						}
						count++;
					}
				}
				
				if(count < 2) {
					System.out.println("No");
					continue main;	
				}
				
				if(!adjMatrix[a][b]) {
					adjMatrix[b][a] = true;
					adjMatrix[a][b] = true;
				} else {
					System.out.println("No");
					continue main;
				}
			}
			
			System.out.println("Yes");
		}
	}
}
