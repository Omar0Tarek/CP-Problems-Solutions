import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa10382 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(br.ready()) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()), l = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken());
			
			Strip[] array = new Strip[n];
			while(n-->0) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken());
				array[n] = new Strip(c + Math.sqrt(r * r - (w / 2.0) * (w / 2.0)), c - Math.sqrt(r * r - (w / 2.0) * (w / 2.0)));
			}
			Arrays.sort(array);
			System.out.println(Arrays.deepToString(array));
			double right = 0, left = 0;
			int counter = 0;
			int steps = 0;
			while(counter < array.length) {
				if(right >= l)
					break;
				if(array[counter].l > right) {
					break;
				} else {
					double tempR = right, tempL = left;
					while(counter < array.length) {
						if(array[counter].l > right)
							break;
						//if(array[counter].l <= tempR && array[counter].r <= tempR)
						//	continue;
						tempR = array[counter].r; tempL = array[counter].l;
						counter++;
					}
					right = tempR;
					left = tempL;
					steps++;
					//System.out.println(right  + " " + left);
				}
			}
			
			System.out.println(right >= l ? steps : -1);
		}
	}
	
	public static class Strip implements Comparable<Strip> {
		
		double r;
		double l;
		
		public Strip(double r, double l) {
			this.l = l;
			this.r = r;
		}
		
		public int compareTo(Strip s) {
			if(this.l == s.l)
				return this.r > s.r ? 1 : -1;
			return this.l > s.l ? 1 : -1;
		}
		
		public String toString() {
			return r + " " + l;
		}
	}
}