import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class UVa507 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int TC = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		for(int t = 1; t <= TC; t++) {
			int n = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			int[] array = new int[n - 1];
			for(int i = 0; i < n - 1; i++)
				array[i] = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			
			int sum = 0, ans = 0, maxBegin = 0, maxEnd = 0, currBegin = 0;
			for(int i = 0; i < n - 1; i++) {
				sum += array[i];
				if(sum >= ans) {
					if(sum > ans || (i + 1) - currBegin > maxEnd - maxBegin) {
						maxBegin = currBegin;
						maxEnd = i + 1;
					}
					ans = sum;
				}
				
				if(sum < 0) {
					sum = 0;
					currBegin = i + 1;
				}
			}
			
			if(ans == 0)
				System.out.printf("Route %d has no nice parts\n", t);
			else
				System.out.printf("The nicest part of route %d is between stops %d and %d\n", t, maxBegin + 1, maxEnd + 1);
		}
	}
}
