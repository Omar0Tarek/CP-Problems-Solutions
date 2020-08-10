import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa626 {
	
	static PrintWriter pw = new PrintWriter(System.out);
	static boolean[][] adjMatrix;
	static int[] perm;
	
	public static boolean check() {
		boolean cycilc =  adjMatrix[perm[0]][perm[1]] && adjMatrix[perm[1]][perm[2]] && adjMatrix[perm[2]][perm[0]];
		boolean order = (perm[0] > perm[1] && perm[1] > perm[2]) || (perm[0] < perm[1] && perm[1] < perm[2]);
		
		return cycilc && order;
	}
	
	public static void print() {
		pw.printf("%d %d %d\n", perm[0] + 1, perm[1] + 1, perm[2] + 1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while(true) {
			if(!br.ready())
				break;
			
			int n = Integer.parseInt(br.readLine());
			adjMatrix = new boolean[n][n];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++)
					adjMatrix[i][j] = st.nextToken().equals("1") ? true : false;
			}
			
			perm = new int[3];
			int count = 0;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(j == i)
						continue;
					for(int k = 0; k < n; k++) {
						if(k == j || k == i)
							continue;
						perm[0] = i;
						perm[1] = j;
						perm[2] = k;
						
						if(check()) {
							print();
							count++;
						}
					}
				}
			}
			
			pw.printf("total:%d\n\n", count);
		}
		
		pw.flush();
		pw.close();
	}
}