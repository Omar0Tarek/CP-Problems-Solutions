import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class UVa750Iterative{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		
		while(t-->0) {
			br.readLine();
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()), column = Integer.parseInt(st.nextToken());
			
			System.out.println("SOLN       COLUMN\n #      1 2 3 4 5 6 7 8\n");
			
			int solution = 1;
			
			for(int i = 1; i <= 8; i++) {
				
				if(column == 1 && row != i)
					continue;
					
				for(int j = 1; j <= 8; j++) {
					
					if(column == 2 && row != j)
						continue;
					
					if(j == i)
						continue;
					
					if(Math.abs(j - i) == 1)
						continue;
					
					for(int k = 1; k <= 8; k++) {
						
						if(column == 3 && row != k)
							continue;
						
						if(k == j || k == i)
							continue;
						
						if(Math.abs(k - j) == 1 || Math.abs(k - i) == 2)
							continue;
						
						for(int l = 1; l <= 8; l++) {
							
							if(column == 4 && row != l)
								continue;
							
							if(l == k || l == j || l == i)
								continue;

							if(Math.abs(l - k) == 1 || Math.abs(l - j) == 2 || Math.abs(l - i) == 3)
								continue;
							
							for(int m = 1; m <= 8; m++) {
								
								if(column == 5 && row != m)
									continue;
								
								if(m == l || m == k || m == j || m == i)
									continue;
								
								if(Math.abs(m - l) == 1 || Math.abs(m - k) == 2 || Math.abs(m - j) == 3 || Math.abs(m - i) == 4)
									continue;
								
								for(int n = 1; n <= 8; n++) {
									
									if(column == 6 && row != n)
										continue;
									
									if(n == m || n == l || n == k || n == j || n == i)
										continue;
									
									if(Math.abs(n - m) == 1 || Math.abs(n - l) == 2 || Math.abs(n - k) == 3 || Math.abs(n - j) == 4 || Math.abs(n - i) == 5)
										continue;
									
									for(int o = 1; o <= 8; o++) {
										
										if(column == 7 && row != o)
											continue;
										
										if(o == n || o == m || o == l || o == k || o == j || o == i)
											continue;
										
										if(Math.abs(o - n) == 1 || Math.abs(o - m) == 2 || Math.abs(o - l) == 3 || Math.abs(o - k) == 4 || Math.abs(o - j) == 5 || Math.abs(o - i) == 6)
											continue;
										
										for(int p = 1; p <= 8; p++) {
											
											if(column == 8 && row != p)
												continue;
											
											if(p == o || p == n || p == m || p == l || p == k || p == j || p == i)
												continue;
											
											if(Math.abs(p - o) == 1 || Math.abs(p - n) == 2 || Math.abs(p - m) == 3 || Math.abs(p - l) == 4 || Math.abs(p - k) == 5 || Math.abs(p - j) == 6 || Math.abs(p - i) == 7)
												continue;
											
											System.out.printf("%2d      " + i + " " + j + " " + k + " " + l + " " + m + " " + n + " " + o + " " + p + "\n", solution++);
											
										}
									}
								}
							}
						}
					}
				}
			}
			
			System.out.print(t == 0 ? "" : "\n");
			
		}
	}
}
