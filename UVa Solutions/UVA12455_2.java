import java.util.Scanner;

public class UVA12455_2 {
	
	static int want;
	static int[] array;
	
	public static boolean Backtrack(int i, int sum) {
		if(i == array.length)
			if(sum == want)
				return true;
			else
				return false;
		return Backtrack(i + 1, sum + array[i]) || Backtrack(i + 1, sum);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		while(n-->0) {
			want = sc.nextInt();
			int m = sc.nextInt();
			array = new int[m];
			for(int i = 0; i < m; i++)
				array[i] = sc.nextInt();
			
			System.out.println(Backtrack(0, 0) ? "YES" : "NO");
		}
	}
}
