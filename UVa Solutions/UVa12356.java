import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Time Limit on SPOJ is 2 seconds which is enough for my sol to pass, if i want to pass UVA 1 sec time limit, i think i should use linkdlist
public class UVa12356 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			if(n + b == 0)
				break;
			
			int[][] array = new int[n][2];
			array[0][0] = -1;
			array[0][1] = 1;
			array[n - 1][0] = n - 2;
			array[n - 1][1] = -1;
			for(int i = 1; i < n - 1; i++) {
				array[i][0] = i - 1;
				array[i][1] = i + 1;
			}
			
			while(b-->0) {
				st = new StringTokenizer(br.readLine());
				int l = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken());
				int left = 0, right = 0;
				if(array[l - 1][0] == -1)
					left = -1;
				if(array[r - 1][1] == -1)
					right = -1;
				
				if(left + right == -2) {
					pw.println("* *");
				} else if(left == -1) {
					right = array[r - 1][1];
					array[right][0] = -1;
					pw.printf("* %d\n", right + 1);
				} else if(right == -1) {
					left = array[l - 1][0];
					array[left][1] = -1;
					pw.printf("%d *\n", left + 1);
				} else {
					right = array[r - 1][1];
					left = array[l - 1][0];
					array[right][0] = left;
					array[left][1] = right;
					pw.printf("%d %d\n", left + 1, right + 1);
				}
			}
			
			pw.println("-");
		}
		
		pw.flush();
		pw.close();
	}
}
