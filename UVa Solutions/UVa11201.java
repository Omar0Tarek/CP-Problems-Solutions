import java.util.Scanner;

public class UVa11201 {
	
	static double[] prob;
	static char[] even;
	static char[] odd;
	
	static String word;
	static int count;
	static double average;
	
	public static void Backtrack(String s) {
		if(s.length() == word.length()) {
			if(!word.equals(s)) {
				average += getAverage(s);
				count++;
			}
			return;
		}
		
		if(s.length() % 2 == 1) {
			for(int i = 0; i < even.length; i++)
				if(!check(s + "" + even[i]))
					Backtrack(s + "" + even[i]);
		} else {
			for(int i = 0; i < odd.length; i++)
				if(!check(s + "" + odd[i]))
					Backtrack(s + "" + odd[i]);
		}
	}
	
	public static double getAverage(String s) {
		double average = 0;
		for(int i = 0; i < s.length(); i++)
			average +=  (i + 1) * prob[s.charAt(i) - 97];
		return average;
	}
	
	public static boolean check(String s) {
		int[] array = new int[26];
		for(int i = 0; i < s.length(); i++)
			if(++array[s.charAt(i) - 97] == 3)
				return true;
		return false;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		prob = new double[] {12.53, 1.42, 4.68, 5.86, 13.68, 0.69, 1.01, 0.70, 6.25, 0.44, 0.00, 4.97, 3.15, 6.71, 8.68, 2.51, 0.88, 6.87, 7.98, 4.63, 3.93, 0.90, 0.02, 0.22, 0.90, 0.52};
		even = new char[] {'a', 'e', 'i', 'o', 'u'};
		odd = new char[] {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z'};
		
		int n = sc.nextInt();
		while(n-->0) {
			word = sc.next();
			double wordAverage = getAverage(word);
			
			average = 0;
			count = 0;
			Backtrack("" + word.charAt(0));
			
			System.out.println(wordAverage < (average / count) ? "below" : "above or equal");
		}
	}
}
