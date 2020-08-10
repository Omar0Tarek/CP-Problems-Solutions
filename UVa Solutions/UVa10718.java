import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class UVa10718 {
	
	static long l, u;
	static int[] mask;
	
	public static long getMask() {
		String s = "";
		for(int i = 0; i < 32; i++)
			s += mask[i];
		//System.out.println(Long.parseUnsignedLong(s, 2) + " " + s);
		return Long.parseUnsignedLong(s, 2);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = 5;
		while(t-->0) {
			st = new StringTokenizer(br.readLine());
			long n = Long.parseLong(st.nextToken());
			l = Long.parseLong(st.nextToken()); u = Long.parseLong(st.nextToken());
			
			int[] original = new int[32];
			mask = new int[32];
			
			String s = Long.toBinaryString(n);
			for(int i = 0; i < s.length(); i++) {
				original[31 - i] = s.charAt(i) - 48;
			}
			
			System.out.println("n : " + Long.toUnsignedString(n, 2));
			long answer = u;
			for(int i = 0; i < 32; i++) {
				mask[i] = 1;
				long curr = getMask();
				if(curr >= l && curr <= u) {
					answer = curr;
				} else if (curr >= u) {
					mask[i] = 0;
				}
			}
			
			s = Long.toBinaryString(answer);
			for(int i = 0; i < s.length(); i++) {
				mask[31 - i] = s.charAt(i) - 48;
			}
			
			for(int i = 0; i < mask.length; i++) {
				if(original[i] == 1 && mask[i] == 1) {
					mask[i] = 0;
					long a = getMask();
					if(a >= l && a <= u) {
						answer = a;
					} else {
						mask[i] = 1;
					}
				}
			}
			
			System.out.println("answer: " + answer);
		}
	}
}
