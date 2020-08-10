import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class UVa11085 {
	
	static int min;
	static int[] original;
	static boolean[] rw = new boolean[8], rd = new boolean[15], ld = new boolean[15];
	
	public static void backtrack(int[] array, int index) {
		if(index == array.length) {
			min = Math.min(getMoves(array), min);
			return;
		}
		
		for(int i = 0; i < 8; i++) {
			if(!rw[i] && !rd[i + index] && !ld[i - index + 7]) {
				rw[i] = rd[i + index] = ld[i - index + 7] = true;
				array[index] = i + 1;
				backtrack(array, index + 1);
				rw[i] = rd[i + index] = ld[i - index + 7] = false;
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
	}*/
	
	public static int getMoves(int[] array) {
		int count = 0;
		for(int i = 0; i < 8; i++)
			if(array[i] != original[i])
				count++;
		return count;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = 1;
		while(true) {
			if(!br.ready())
				break;
			
			int[] array = new int[8];
			st = new StringTokenizer(br.readLine());
			original = new int[8];
			for(int i = 0; i < 8; i++)
				original[i] = Integer.parseInt(st.nextToken());
			
			min = 8;
			backtrack(array, 0);
			System.out.println("Case " + t++ + ": " + min);
		}
	}
}
