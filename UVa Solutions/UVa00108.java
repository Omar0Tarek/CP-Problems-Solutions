import java.util.Arrays;
import java.util.Scanner;

public class UVa00108 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[][] array = new int[n][n];
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++) {
				array[i][j] = sc.nextInt();
				if(j > 0)
					array[i][j] += array[i][j - 1];
			}
		
		for(int i = 0; i < n; i++)
			for(int j = 1; j < n; j++)
				array[j][i] += array[j - 1][i];
		
		int max = -127*100*100;
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				for(int k = i; k < n; k++)
					for(int l = j; l < n; l++) {
						int ans = array[k][l];
						if(i > 0) ans -= array[i - 1][l];
						if(j > 0) ans -= array[k][j - 1];
						if(i > 0 && j > 0) ans += array[i - 1][j - 1];
						max = Math.max(max, ans);
					}
		
		System.out.println(max);
	}
}
