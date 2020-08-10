import java.util.Scanner;

public class UVa624 {
	
	static int[] array;
	static int max = 0, want;
	static String maxString = "";
	
	public static void Backtrack(int i, int sum, String s) {
		if(i == array.length) {
			if(sum <= want)
				if(sum > max) {
					max = sum;
					maxString = s;
				}
			return;
		}
		Backtrack(i + 1, sum + array[i], s + array[i] + " ");
		Backtrack(i + 1, sum, s);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt()) {
			want = sc.nextInt();
			int n = sc.nextInt();
			array = new int[n];
			for(int i = 0; i < n; i++)
				array[i] = sc.nextInt();
			
			Backtrack(0, 0, "");
			System.out.println(maxString + "sum:" + max);
			
			max = 0;
			maxString = "";
		}
	}
}
