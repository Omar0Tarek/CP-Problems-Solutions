import java.util.*;
import java.io.*;

public class UVa00326 {
	
	public static long nCk(int n, int k) {
		if(n < k)
			return 0;
		double res = 1;
		for(int i = 1; i <= k; i++)
			res = (n - k + i) * res / i;
		return (long)(0.01 + res);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			if(st.countTokens() == 1)
				break;
			int n = Integer.parseInt(st.nextToken());
			ArrayList<Integer> gen = new ArrayList<>();
			ArrayList<Integer> list = new ArrayList<>();
			for(int i = 0; i < n; i++)
				gen.add(Integer.parseInt(st.nextToken()));
			list.add(gen.get(gen.size() - 1));
			while(true) {
				if(gen.size() == 1)
					break;
				ArrayList<Integer> temp = new ArrayList<>();
				for(int i = 0; i < gen.size() - 1; i++)
					temp.add(gen.get(i + 1) - gen.get(i));
				gen = temp;
				list.add(gen.get(gen.size() - 1));
			}
			
			int k = Integer.parseInt(st.nextToken());
			long ans = 0;
			for(int i = 0; i < list.size(); i++)
				ans += list.get(i) * nCk(i + k - 1, i);
			
			pw.printf("Term %d of the sequence is %d\n", n + k, ans);
		}
		
		pw.flush();
		pw.close();
	}
}