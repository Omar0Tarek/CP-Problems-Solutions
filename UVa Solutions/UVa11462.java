import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class UVa11462 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder("");
		
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0)
				break;
			
			int[] count = new int[100];
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens())
				count[Integer.parseInt(st.nextToken())]++;
			
			for(int i = 0; i < count.length; i++) {
				while(count[i]-->0) {
					sb.append(i + " ");
				}
			}
			sb.setCharAt(sb.length() - 1, '\n');
		}
		
		System.out.print(sb.toString());
	}
}
