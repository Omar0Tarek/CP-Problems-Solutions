import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa11292 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()), t = Integer.parseInt(st.nextToken());
			
			if(n + t == 0)
				break;
			else if(t < n) {
				System.out.println("Loowater is doomed!");
				return;
			}
			
			int[] heads = new int[n], knights = new int[t];
			for(int i = 0; i < n; i++)
				heads[i] = Integer.parseInt(br.readLine());
			for(int i = 0; i < t; i++)
				knights[i] = Integer.parseInt(br.readLine());
			Arrays.sort(heads);
			Arrays.sort(knights);

			int sum = 0;
			int i = 0, j = 0;
			for(i = 0; i < t; i++) {
				if(j == n)
					break;
				if(knights[i] >= heads[j]) {
					sum += knights[i];
					j++;
				}
			}
			if(j == n)
				System.out.println(sum);
			else
				System.out.println("Loowater is doomed!");
		}
	}
}
