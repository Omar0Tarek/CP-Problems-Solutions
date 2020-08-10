import java.util.*;
public class UVa11933{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			int n = sc.nextInt();
			if(n == 0)
				break;
			
			String binary = Integer.toBinaryString(n);
			int a = 0, b = 0, oneCounter = 0;
			int i = binary.length();
			while(i-->0) {
				if(binary.charAt(i) == 49) {
					int current = (int)Math.pow(2, binary.length() - (i + 1));
					if(oneCounter % 2 == 0)
						a += current;
					else
						b += current;
					oneCounter++;
				}
			}
			
			System.out.println(a + " " + b);
		}
	}
}
