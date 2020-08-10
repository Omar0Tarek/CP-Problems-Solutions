import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class UVa1121 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			if(!br.ready())
				break;
			
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()), s = Integer.parseInt(st.nextToken());
			
			int[] array = new int[n];
			st = new StringTokenizer(br.readLine());
			long checkSum = 0;
			for(int i = 0; i < array.length; i++) {
				array[i] = Integer.parseInt(st.nextToken());
				checkSum += array[i];
			}
			
			long sum = 0;
			int i = 0, j = 0, length = array.length;
			while(j < array.length) {
				sum += array[j++];
				while(sum >= s) {
					length = Math.min(length, j - i);
					sum -= array[i++];
				}
			}
			
			
			if(length == n && checkSum < s)
				System.out.println(0);
			else
				System.out.println(length);
		}
	}
}