import java.util.Scanner;

public class UVa11565 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		while(n-->0) {
			int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
			int lim = (int)Math.sqrt(c);
			
			boolean flag = true;
			bigFor: for(int x = -lim; x <= lim; x++) {
				for(int y = -lim; y <= lim; y++) {
					int z = a - x - y;
					if(x != y && x != z && check(x, y, z, b, c)) {
						flag = false;
						System.out.println(x + " " + y + " " + z);
						break bigFor;
					}
				}
			}
			
			if(flag)
				System.out.println("No solution.");
		}
		
	}
	
	public static boolean check(int x, int y, int z, int a, int c) {
		if((x * y * z) == a && (x * x + y * y + z * z) == c)
			return true;
		return false;
	}
}
