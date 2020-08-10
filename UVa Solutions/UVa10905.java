import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.StringTokenizer;
import java.util.Arrays;

public class UVa10905 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0)
				break;
			
			Num[] array = new Num[n];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < array.length; i++)
				array[i] = new Num(Integer.toString(Integer.parseInt(st.nextToken())));
			
			Arrays.sort(array);
			//System.out.println(Arrays.deepToString(array));
			for(int i = 0; i < array.length; i++)
				System.out.print(array[i].number);
			System.out.print("\n");
			
		}
	}
	
	public static class Num implements Comparable<Num>{
		String number;
		
		public Num(String number) {
			this.number = number;
		}
		
		@Override
		public int compareTo(Num n) {
			
			long compare = Long.compare(Long.parseLong(n.number + "" + this.number), Long.parseLong(this.number + "" + n.number));
			return compare > 0 ? 1 : compare == 0 ? 0 : -1;
			/*int i = 0;
			for(; i < Math.min(this.number.length(), n.number.length()); i++)
				if(this.number.charAt(i) != n.number.charAt(i))
					return n.number.charAt(i) - this.number.charAt(i);
			int compare = 0;
			if(i < this.number.length()) {
				compare = n.number.compareTo(this.number.substring(i));
			} else {
				compare = n.number.substring(i).compareTo(this.number);
			}
			
			if(compare == 0)
				return this.number.length() - n.number.length();
			return compare;
			
			if(this.number.length() > n.number.length()) {
				int x = n.number.length();
				for(; x < this.number.length(); x += n.number.length()) {
					if(x < this.number.length() && !this.number.substring(x).equals(n.number))
						return n.number.compareTo(this.number.substring(x));
				}
				return this.number.substring(x - n.number.length()).compareTo(n.number);
			} else {
				int x = this.number.length();
				for(; x < n.number.length(); x += this.number.length()) {
					if(x < n.number.length() && !n.number.substring(x).equals(this.number))
						return n.number.substring(x).compareTo(this.number);
				}
				return n.number.substring(x - this.number.length()).compareTo(this.number);
			}
			*/
		}
		
		@Override
		public String toString() {
			return this.number + "\n";
		}
	}
}