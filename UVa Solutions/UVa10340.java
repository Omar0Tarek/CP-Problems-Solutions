import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class UVa10340 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			if(!br.ready())
				break;
			
			st = new StringTokenizer(br.readLine());
			String a = st.nextToken(), b = st.nextToken();
			
			int j = 0;
			for(int i = 0; i < b.length() && j < a.length(); i++) {
				if(a.charAt(j) == b.charAt(i))
					j++;
			}
			
			System.out.println(j == a.length() ? "Yes" : "No");
		}
	}
}
