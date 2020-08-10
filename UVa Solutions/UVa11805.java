import java.util.Scanner;

public class UVa11805 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for(int i = 1; i <= t; i++) {
			int n = sc.nextInt(), k = sc.nextInt(), p = sc.nextInt();
			System.out.printf("Case %d: %d\n", i, ((((k + p - 1) % n) + n) % n) + 1);
		}
	}
}
