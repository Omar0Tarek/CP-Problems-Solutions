import java.util.*;
import java.io.*;
public class UVa12455{
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		for(int counter = 0; counter < t; counter++) {
			int want = Integer.parseInt(br.readLine());
			int number = Integer.parseInt(br.readLine());
			
			int[] barLength = new int[number];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < number; i++)
				barLength[i] = Integer.parseInt(st.nextToken());
			
			if(isValid(want, number, barLength))
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
	
	public static boolean isValid(int finalLength, int number, int[] barLength) {
		int max = (int)Math.pow(2, number);
		for(int i = 0; i < max; i++) {
			int sum = 0;
			for(int n  = 0; n < number; n++) {
				if((i & (1 << n)) == (1 << n)) {
					sum += barLength[number - (n + 1)];
				}
			}
			if(sum == finalLength)
				return true;
		}
		return false;
	}
}
