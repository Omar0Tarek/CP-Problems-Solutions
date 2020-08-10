import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class UVa1251 {
	
	static int n;
	static String[][] subs;
	static String want;
	static int min;
	
	public static void Backtrack(String s, int i, int c) {
		s = change(s, i);
		if(want.equals(s)) {
			min = Math.min(min, c);
			return;
		}
		
		for(int j = 0; j < n; j++)
			if(s.contains(subs[j][0]))
				Backtrack(s, j, c + 1);
	}
	
	public static String change(String s, int i) {
		String z = "";
		while(true) {
			if(!s.contains(subs[i][0]) && !want.equals(s))
				break;
			int n = s.indexOf(subs[i][0]);
			z = s.substring(0, n) + subs[i][1] + s.substring(n + subs[i][0].length());
			s = z;
		}
		return z;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			n = Integer.parseInt(br.readLine());
			if(n == 0)
				break;
			
			subs = new String[n][2];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				subs[i][0] = st.nextToken();
				subs[i][1] = st.nextToken();
			}
			
			String s = br.readLine();
			want = br.readLine();
			
			min = 100;
			for(int i = 0; i < n; i++)
				if(s.contains(subs[i][0]))
					Backtrack(s, i, 1);
			System.out.println(min == 100 ? -1 : min);
		}
	}
}
