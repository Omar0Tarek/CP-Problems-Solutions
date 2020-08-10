import java.util.*;
import java.io.*;

public class UVa11401 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		ArrayList<Integer> list = new ArrayList<>();
		int max = 0;
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n < 3)
				break;
			list.add(n);
			max = Math.max(max, n);
		}
		
		long[] ans = new long[max + 1];
		ans[3] = 0;
		for(int i = 4; i <= max; i++) {
			int x = (i - 2) / 2;
			ans[i] = 1l * ans[i - 1] + 1l * x * (i - x) - 1l * 2 * x;
		}
		
		for(int x : list)
			pw.println(ans[x]);
		
		pw.flush();
		pw.close();
	}
}
