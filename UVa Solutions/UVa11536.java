import java.util.Arrays;
import java.util.Scanner;

public class UVa11536 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			
			int n = sc.nextInt(), m = sc.nextInt(), k = sc.nextInt();
			
			int[] array = new int[n];
			array[0] = 1;
			array[1] = 2;
			array[2] = 3;
			int sum = 6;
			for(int i = 3; i < n; i++) {
				array[i] = (sum % m) + 1;
				sum += array[i] - array[i - 3];
			}
			
			int[] count = new int[k];
			int length = n;
			int i = 0, j = 0, freq = 0;
			while(j < array.length) {
				if(array[j++] <= k && ++count[array[j - 1] - 1] == 1)
					freq++;
				if(freq == k) {
					while(i < j && freq == k) {
						length = Math.min(length, j - i);
						if(array[i++] <= k && --count[array[i - 1] - 1] == 0)
							freq--;
					}
				}
			}
			
			
			if(length == n && freq != k)
				System.out.printf("Case %d: sequence nai\n", t);
			else
				System.out.printf("Case %d: %d\n", t, length);
		}
	}
}
