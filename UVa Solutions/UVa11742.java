import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class UVa11742 {
	
	static int n, m;
	static int[][] conditions;
	static int count = 0;
	
	public static void Backtrack(int n, String s) {
		if(s.length() == n && isValid(s)) {
			count++;
			return;
		}
		
		for(int i = 1; i <= n; i++) {
			if(!check(s + "" + i))
				continue;
			
			Backtrack(n, s + "" + i);
		}
	}
	
	public static boolean check(String s) {
		int[] count = new int[n];
		for(int i = 0; i < s.length(); i++)
			if(++count[s.charAt(i) - 49] == 2)
				return false;
		return true;
		
	}
	
	public static boolean isValid(String s) {
		int[] index = new int[n];
		for(int i = 0; i < s.length(); i++)
			index[s.charAt(i) - 49] = i;
		
		for(int i = 0; i < m; i++) {
			int c = conditions[i][2];
			if(c > 0) {
				if(Math.abs(index[conditions[i][0]] - index[conditions[i][1]]) > c)
					return false;
			} else {
				if(Math.abs(index[conditions[i][0]] - index[conditions[i][1]]) < -c)
					return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());
			
			if(n + m == 0)
				break;
			
			conditions = new int[m][3];
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 3; j++)
					conditions[i][j] = Integer.parseInt(st.nextToken());
			}
			
			count = 0;
			Backtrack(n, "");
			System.out.println(count);
		}
	}
}


//Worst Solution ever
/*import java.util.*;
import java.io.*;
public class UVa11742 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		while(true) {
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
			if(n == m && m == 0)
				break;
			
			int possible = 0;
			int[][] array = getPars(m);
			
			int max = (int)Math.pow(n, n);
			bigFor: for(int i = 0; i < max; i++) {
				String perm = "";
				int copy = i;
				while(copy > 0) {
					if(perm.contains(Integer.toString(copy % n)))
						continue bigFor;
					perm = (copy % n) + perm;
					copy /= n;
				}
				
				perm = addZeros(perm, n);
				if(perm == "no")
					continue bigFor;
				
				//System.out.println(i + " " + perm);
				//System.out.println(Arrays.deepToString(array));
				
				if(isValid(perm, array)) {
					possible++;
					//System.out.println(possible + "\n");
				}
			}
			
			System.out.println(possible);
		}
	}
	
	
	public static int[][] getPars(int m) throws IOException {
		int[][] array = new int[m][3];
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++)
				array[i][j] = Integer.parseInt(st.nextToken());
		}
		return array;
	}
	
	public static String addZeros(String s, int n) {
		while(s.length() < n) {
			if(s.contains("0"))
				return "no";
			s = "0" + s;
		}
		return s;
	}
	
	public static boolean isValid(String s, int[][] array) {
		for(int i = 0; i < array.length; i++) {
			int difference = Math.abs(s.indexOf(Integer.toString(array[i][0])) - s.indexOf(Integer.toString(array[i][1])));
			//System.out.println(difference + " " + array[i][2]);
			
			if(array[i][2] < 0) {
				if(difference < Math.abs(array[i][2]))
					return false;
			} else {
				if(difference > array[i][2])
					return false;
			}
		}
		return true;
	}
	
}
*/