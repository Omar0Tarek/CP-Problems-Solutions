import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class UVa750 {
	
	static int rowIn, colIn;
	static int count;
	
	static boolean[] rd = new boolean[15], ld = new boolean[15], rw = new boolean[8];
	
	public static void backtrack(int[] array, int index) {
		if(index == array.length) {
			System.out.printf("%2d" + "      " + array[0] + " " + array[1] + " " + array[2] + " " + array[3] + " " + array[4] + " " + array[5] + " " + array[6] + " " + array[7] + "\n", count++);
			return;
		}
		
		for(int i = 1; i <= 8; i++) {
			/*if(index + 1 == colIn) {
				if(check(array, index, rowIn)) {
					array[index] = rowIn;
					backtrack(array, index + 1);
					return;
				} else {
					return;
				}
			}
			if(check(array, index, i)) {
				array[index] = i;
				backtrack(array, index + 1);
			}*/
			
			if(index + 1 == colIn) {
				if(!rw[rowIn - 1] && !rd[rowIn - 1 + index] && !ld[rowIn - 1 - index + 7]) {
					rw[rowIn - 1] = rd[rowIn - 1 + index] = ld[rowIn - 1 - index + 7] = true;
					array[index] = rowIn;
					backtrack(array, index + 1);
					rw[rowIn - 1] = rd[rowIn - 1 + index] = ld[rowIn - 1 - index + 7] = false;
				}
				return;
			}
			
			if(!rw[i - 1] && !rd[i - 1 + index] && !ld[i - 1 - index + 7]) {
				rw[i - 1] = rd[i - 1 + index] = ld[i - 1 - index + 7] = true;
				array[index] = i;
				backtrack(array, index + 1);
				rw[i - 1] = rd[i - 1 + index] = ld[i - 1 - index + 7] = false;
			}
		}
	}
	
	/*public static boolean check(int[] array, int index, int n) {
		for(int i = 0; i < index; i++)
			if(array[i] == n)
				return false;
		for(int i = 0; i < index; i++)
			if(Math.abs(index - i) == Math.abs(n - array[i]))
				return false;
		return true;
	}
	*/
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			br.readLine();
			st = new StringTokenizer(br.readLine());
			rowIn = Integer.parseInt(st.nextToken()); colIn = Integer.parseInt(st.nextToken());
			int[] array = new int[8];
			System.out.println("SOLN       COLUMN\n #      1 2 3 4 5 6 7 8\n");
			count = 1;
			backtrack(array, 0);
			System.out.print(t == 0 ? "" : "\n");
		}
	}
}
