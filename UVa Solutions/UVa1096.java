import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Arrays;
import java.util.Collections;

public class UVa1096 {
	
	static int n, isl1, isl2;
	static double[][] d;
	static double[][][] memo;
	
	static ArrayList<Integer> moves;
	static Stack<Integer> stack;
	
	public static double dp(int p1, int p2, int s) {
		if(p1 == n - 1)
			return d[p2][n - 1];
		if(p2 == n - 1)
			return d[p1][n - 1];
		
		if(memo[p1][p2][s] > -1)
			return memo[p1][p2][s];
		
		int curr = Math.max(p1,  p2) + 1;
		if(curr == isl1) {
			return memo[p1][p2][s] = Math.min(d[p1][curr] + dp(curr, p2, 1), d[p2][curr] + dp(p1, curr, 2));
		}
		
		if(curr == isl2) {
			if(s == 1)
				return memo[p1][p2][s] = d[p2][curr] + dp(p1, curr, s);
			else
				return memo[p1][p2][s] = d[p1][curr] + dp(curr, p2, s);
		}
		
		return memo[p1][p2][s] = Math.min(d[p1][curr] + dp(curr, p2, s), d[p2][curr] + dp(p1, curr, s));
	}
	
	public static void constructPath(int p1, int p2, int s) {
		if(p1 == n - 1 || p2 == n - 1) {
			return;
		}
		
		int curr = Math.max(p1,  p2) + 1;
		if(curr == isl1) {
			if(memo[p1][p2][s] == d[p1][curr] + dp(curr, p2, 1)) {
				moves.add(curr);
				constructPath(curr, p2, 1);
			} else {
				stack.push(curr);
				constructPath(p1, curr, 2);
			}
		} else if(curr == isl2) {
			if(s == 1) {
				stack.push(curr);
				constructPath(p1, curr, s);
			} else {	
				moves.add(curr);
				constructPath(curr, p2, s);
			}
		} else {
			if(d[p1][curr] + dp(curr, p2, s) == memo[p1][p2][s]) {
				moves.add(curr);
				constructPath(curr, p2, s);
			} else {
				stack.push(curr);
				constructPath(p1, curr, s);
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		int counter = 1;
		while(true) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int one = Integer.parseInt(st.nextToken()), two = Integer.parseInt(st.nextToken());
			if(n + one + two == 0)
				break;
			
			isl1 = Math.min(one, two);
			isl2 = Math.max(one, two);
			
			
			int[][] array = new int[n][2];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				array[i][0] = Integer.parseInt(st.nextToken());
				array[i][1] = Integer.parseInt(st.nextToken());
			}
			
			
			d = new double[n][n];
			for(int i = 0; i < n; i++) {
				for(int j = i + 1; j < n; j++) {
					double dist = Math.hypot(array[i][0] - array[j][0], array[i][1] - array[j][1]);
					d[i][j] = d[j][i] = dist;
				}
			}
			
						
			memo = new double[n][n][3];
			for(int i = 0; i < n; i++)
				for(int j = 0; j < n; j++)
					Arrays.fill(memo[i][j], -100);
			
						
			double x = dp(0, 0, 0);
			pw.printf("Case %d: %.2f\n", counter++, dp(0, 0, 0));
						
			
			moves = new ArrayList<Integer>();
			stack = new Stack<Integer>();
			
			moves.add(0);
			constructPath(0, 0, 0);
			
			while(!stack.isEmpty())
				moves.add(stack.pop());
			moves.add(0);
			
			if(moves.get(1) != 1)
				Collections.reverse(moves);
			
			for(int i = 0; i < moves.size(); i++)
				pw.print(moves.get(i) + (i == moves.size() - 1 ? "\n" : " "));
		}
		
		pw.flush();
		pw.close();
	}
	
	public static class Pair {
		int x, y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
