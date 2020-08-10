import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa10911 {
	
	static double INF = 1e9;
	static int n;
	static Pair[] array;
	static double[] memo;
	
	public static double dp(int msk) {
		if(msk == (1<<n) - 1)
			return 0;
		
		if(memo[msk] != -1)
			return memo[msk];
		
		int free = 0;
		for(int i = 0; i < n; i++)
			if(((1<<i) & msk) == 0) {
				free = i;
				break;
			}
		
		double min = INF;
		for(int i = 0; i < n; i++)
			if(i != free && ((1<<i) & msk) == 0)
				min = Math.min(min, dist(free, i) + dp((msk | ((1<<free))|(1<<i))));
		
		return memo[msk] = min;
	}
	
	public static double dist(int x, int y) {
		return Math.sqrt(Math.pow(array[x].x - array[y].x, 2) + Math.pow(array[x].y - array[y].y, 2));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int counter = 1;
		while(true) {
			n = Integer.parseInt(br.readLine()) * 2;
			if(n == 0)
				break;
			
			array = new Pair[n];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				st.nextToken();
				array[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			memo = new double[(1<<n) - 1];
			Arrays.fill(memo, -1);
			
			System.out.printf("Case %d: %.2f\n", counter++, dp(0));
			
		}
	}
	
	public static class Pair {
		int x, y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
