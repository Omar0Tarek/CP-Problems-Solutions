import java.util.Scanner;

public class UVa441{
	
	static int a, b, c, d, e, f;
	static int[] array;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		while(true) {
			if(n == 0)
				break;
			
			array = new int[n];
			for(int i = 0; i < n; i++)
				array[i] = sc.nextInt();
			
			for(a = 0; a < n; a++) {
				for(b = a + 1; b < n; b++) {
					for(c = b + 1; c < n; c++) {
						for(d = c + 1; d < n; d++) {
							for(e = d + 1; e < n; e++) {
								for(f = e + 1; f < n; f++) {
									print();
								}
							}
						}
					}
				}
			}
			n = sc.nextInt();
			System.out.print(n == 0 ? "" : "\n");
		}
	}
	
	public static void print() {
		System.out.println(array[a] + " " + array[b] + " " + array[c] + " " + array[d] + " " + array[e] + " " + array[f]);
	}
}
