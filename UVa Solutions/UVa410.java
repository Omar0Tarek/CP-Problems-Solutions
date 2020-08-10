import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa410 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int counter = 1;
		while(true) {
			if(!br.ready())
				break;
			
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken()), s = Integer.parseInt(st.nextToken());
			
			int[] cSum = new int[c];
			String[] array = new String[c];
			for(int i = 0; i < c; i++)
				array[i] = " " + i + ":";
			
			int[] sp = new int[s];
			int sum = 0;
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < s; i++) {
				sp[i] = Integer.parseInt(st.nextToken());
				sum += sp[i];
			}
			Arrays.sort(sp);
			
			int j = s - 1;
			for(int i = c - 1; i >= 0 && j >= 0; i--) {
				array[i] += " " + sp[j];
				cSum[i] += sp[j];
				j--;
			}
			
			for(int i = 0; j >= 0; i++) {
				array[i] += " " + sp[j];
				cSum[i] += sp[j];
				j--;
			}
			
			System.out.printf("Set #%d\n", counter++);
			double avg = sum * 1.0 / c, im = 0;
			for(int i = 0; i < array.length; i++) {
				System.out.println(array[i]);
				im += Math.abs(cSum[i] - avg);
			}
			System.out.printf("IMBALANCE = %.5f\n\n", im);
		}
	}
}
