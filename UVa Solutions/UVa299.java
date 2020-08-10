import java.util.Scanner;

public class UVa299 {
	
	static int steps = 0;
	
	public static void BubbleSort(int[] array) {
		boolean flag = false;
		int i = 1;
		while(!flag) {
			flag = true;
			for(int j = 0; j < array.length - i; j++) {
				if(array[j + 1] < array[j]) {
					int temp = array[j + 1];
					array[j + 1] = array[j];
					array[j] = temp;
					flag = false;
					steps++;
				}
			}
			i++;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder("");
		
		int t = sc.nextInt();
		while(t-->0) {
			int n = sc.nextInt();
			int[] array = new int[n];
			for(int i = 0; i < n; i++)
				array[i] = sc.nextInt();
			
			steps = 0;
			BubbleSort(array);
			
			sb.append("Optimal train swapping takes " + steps + " swaps." + "\n");
		}
		
		System.out.print(sb.toString());
	}
}
