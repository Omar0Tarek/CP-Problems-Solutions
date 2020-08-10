import java.util.*;
import java.io.*;

public class UVa11413 {
	
	static int v, c;
	static int[] array;
	
	public static int binarySearch(int end) {
		int start = 0;
		while(start < end) {
			int mid = start + (end - start) / 2;
			if(valid(mid))
				end = mid;
			else
				start = mid + 1;
		}
		
		return start;
	}
	
	public static boolean valid(int k) {
		int curr = k;
		int remC = c;
		for(int i = 0; i < v; i++) {
			if(remC == 0)
				return false;
			if(array[i] <= curr) {
				curr -= array[i];
			} else {
				if(array[i] > k) {
					return false;
				}
				remC--;
				curr = k;
				i--;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		while(true) {
			if(!br.ready())
				break;
			
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			array = new int[v];
			int max = 0;
			for(int i = 0; i < v; i++) {
				array[i] = Integer.parseInt(st.nextToken());
				max = Math.max(max, array[i]);
			}
			
			int ans = binarySearch(max * v);
			pw.println(ans);
		}
		
		pw.flush();
		pw.close();
	}
}
