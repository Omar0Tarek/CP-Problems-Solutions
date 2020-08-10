import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class UVa524 {
	
	static int[] perm;
	static boolean[] taken;
	static int n;
	
	public static void print() {
		for(int i = 0; i < n; i++)
			System.out.print(perm[i] + (i == n - 1 ? "" : " "));
		System.out.print("\n");
	}
	
	public static boolean isPrime(int n) {
		for(int i = 2; i < n; i++)
			if(n % i == 0)
				return false;
		return true;
	}
	
	public static void backtrack(int i) {
		if(i == n) {
			print();
			return;
		}
		
		for(int j = 2; j <= n; j++) {
			if(!taken[j]) {
				if(i == n - 1) {
					if(isPrime(perm[i - 1] + j) && isPrime(1 + j)) {
						taken[j] = true;
						perm[i] = j;
						backtrack(i + 1);
						taken[j] = false;
					}
				} else {
					if(isPrime(perm[i - 1] + j)) {
						taken[j] = true;
						perm[i] = j;
						backtrack(i + 1);
						taken[j] = false;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int counter = 1;
		while(true) {
			if(!br.ready())
				break;
			
			if(counter != 1)
				System.out.print("\n");
			
			n = Integer.parseInt(br.readLine());
			perm = new int[n];
			taken = new boolean[n + 1];
			
			perm[0] = 1;
			taken[1] = true;
			
			System.out.printf("Case %d:\n", counter++);
			backtrack(1);
		}
	}
}
