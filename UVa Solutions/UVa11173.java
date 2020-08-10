import java.util.Scanner;
public class UVa11173{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for(int i = 0; i < t; i++) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			String binary = Integer.toBinaryString(k);
			int out = (binary.charAt(0) - 48) * (int)Math.pow(2, (binary.length() - 1));
			for(int j = 1; j < binary.length(); j++) {
				out += (binary.charAt(j) ^ binary.charAt(j - 1)) * (int)Math.pow(2, (binary.length() - (j + 1)));
			}
			System.out.println(out);
		}
	}
}
