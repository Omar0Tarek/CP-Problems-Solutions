import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa11450 {
	
	static int m, c;
	//static int max;
	static int[][] array;
	static int[][] states;
	
	/*public static int backtrack(int i, int money) {
		if(money > m)
			return - 2;
		
		if(i == c)
			return money;
		
		if(states[i][money] != -1)
			return states[i][money];
		
		int max = -2;
		for(int j = 0; j < array[i].length; j++) {
			int x = array[i][j];
			if(money + x <= m) {
				states[i + 1][money + x] = backtrack(i + 1, money + x);
				max = Math.max(max, states[i + 1][money + x]);
			}
		}
		return max;
	}*/
	/*
	public static int backtrack(int i, int money) {
		if(money > m)
			return - 2;
		
		if(i == c)
			return money;
		
		if(states[i][money] != -1)
			return states[i][money];
		
		int max = -2;
		for(int j = 0; j < array[i].length; j++) {
			int x = array[i][j];
			if(money + x <= m)
				max = Math.max(max, backtrack(i + 1, money + x));
		}
		
		return states[i][money] = max;
	}
	*/
	/*
	public static int backtrack(int i, int money) {
		if(money > m)
			return -2;
		
		if(i == c)
			return money;
		
		if(states[i][money] != -1)
			return states[i][money];
		
		int max = -2;
		for(int j : array[i])
			if(money + j <= m)
				max = Math.max(max, backtrack(i + 1, money + j));
		
		return states[i][money] = max;
		
	}
	*/
	
	static int INF = (int) 1e9;
	
	public static int backtrack(int i, int money) {
		if(money < 0)
			return INF;
		if(i == c)
			return money;
		
		if(states[i][money] != -1)
			return states[i][money];
		
		int min = INF;
		for(int j : array[i])
			if(money - j >= 0)
				min = Math.min(min, backtrack(i + 1, money - j));
		
		return states[i][money] = min;
	}
	
	/*
	public static void printChoice(int i, int money) {
		if(money < 0 || i == c)
			return;
		
		for(int j = 0; j < array[i].length; j++)
			if(backtrack(i + 1, money - array[i][j]) == states[i][money]) {
				System.out.println((i + 1) + " " + (j + 1) + " : " + array[i][j]);
				printChoice(i + 1, money - array[i][j]);
				break;
			}
	}
	*/
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken()); c = Integer.parseInt(st.nextToken());
			array = new int[c][];
			
			for(int i = 0; i < array.length; i++) {
				st = new StringTokenizer(br.readLine());
				array[i] = new int[Integer.parseInt(st.nextToken())];
				for(int j = 0; j < array[i].length; j++)
					array[i][j] = Integer.parseInt(st.nextToken());
			}
			
			states = new int[c][201];
			for(int i = 0; i < c; i++)
				Arrays.fill(states[i], -1);
			
			int min = backtrack(0, m);
			System.out.println(min == INF ? "no solution" : m - min);
			
			
			/*
			for(int i = 0; i <= 200; i++) {
				int max = -INF;
				for(int j = 0; j < array[c - 1].length; j++)
					if(i - array[c - 1][j] >= 0)
						max = Math.max(max, m - (i - array[c - 1][j]));
				states[c - 1][i] = max;
			}
			
			for(int i = c - 2; i >= 0; i--) {
				for(int j = 0; j <= 200; j++) {
					int max = -INF;
					for(int k = 0; k < array[i].length; k++)
						if(j - array[i][k] >= 0)
							max = Math.max(max, states[i + 1][j - array[i][k]]);
					states[i][j] = max;
				}
			}
			
			System.out.println(states[0][m] == -INF ? "no solution" : states[0][m]);
			*/
			
			//printChoice(0, m);
		}
				
	}
}
