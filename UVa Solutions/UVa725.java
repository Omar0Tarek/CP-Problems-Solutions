import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class UVa725 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		while(true) {
			if(n == 0)
				break;
			
			boolean flag = true;
			
			for(int i = n * 1234; i < 98765; i += n) {
				if(check(Integer.toString(i), Integer.toString(i / n))) {
					flag = false;
					System.out.println(fix(Integer.toString(i)) + " / " + fix(Integer.toString(i / n))  + " = " + n);
				}
			}
			
			if(flag)
				System.out.println("There are no solutions for " + n + ".");
			
			n = Integer.parseInt(br.readLine());
			System.out.print(n == 0 ? "" : "\n");
		}
	}
	
	public static boolean check(String s1, String s2) {
		String s = fix(s1) + fix(s2);
		
		int[] count = new int[10];
		for(int i = 0; i < s.length(); i++) {
			if(count[s.charAt(i) - 48] != 0)
				return false;
			count[s.charAt(i) - 48]++;
		}
		return true;
	}
	
	public static String fix(String s) {
		while(s.length() < 5)
			s = "0" + s;
		return s;
	}
}
