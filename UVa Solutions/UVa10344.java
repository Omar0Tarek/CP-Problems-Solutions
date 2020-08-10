import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class UVa10344 {
	
	static char[] choice = {'+', '-', '*'};
	static char[] pre = new char[4];
	static int counter = 0;
	static char[][] op = new char[81][4];
	
	
	public static void preprocess(int index) {
		if(index == 4) {
			for(int i = 0; i < 4; i++)
				op[counter][i] = pre[i];
			counter++;
			return;
		}
		
		for(int i = 0; i < 3; i++) {
			pre[index] = choice[i];
			preprocess(index + 1);
		}
	}

	static int[] tuple;
	static int[] perm;
	static boolean[] taken;
	static boolean isValid;
	
	
	public static void backtrack(int index) {
		if(index == 5) {
			if(check())
				isValid = true;
			return;
		}
		
		for(int i = 0; i < 5; i++) {
			if(!taken[i]) {
				taken[i] = true;
				perm[index] = tuple[i];
				backtrack(index + 1);
				taken[i] = false;
			}
		}
	}
	
	public static boolean check() {
		for(int i = 0; i < op.length; i++) {
			int x = sum(perm[0], perm[1], op[i][0]);
			x = sum(x, perm[2], op[i][1]);
			x = sum(x, perm[3], op[i][2]);
			x = sum(x, perm[4], op[i][3]);
			
			if(x == 23)
				return true;
		}
		return false;
	}
	
	public static int sum(int x, int y, char c) {
		switch(c) {
			case '+': return x + y;
			case '-': return x - y;
			default: return x * y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		preprocess(0);
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			tuple = new int[5];
			for(int i = 0; i < 5; i++)
				tuple[i] = Integer.parseInt(st.nextToken());
			if(tuple[0] == 0)
				break;
			
			taken = new boolean[5];
			perm = new int[5];
			isValid = false;
			
			backtrack(0);
			
			System.out.println(isValid ? "Possible" : "Impossible");
		}
	}
}


//Not Working Solution
/*

public class UVa10344 {
	
	static int[] numbers = new int[5];
	static char[] op = new char[] {'+', '-', '*'};
	
	static boolean flag = false;
	
	public static void Backtrack(String n, int k) {
		if(k == 5) {
			Backtrack(n, "", 0);
			return;
		}
		
		for(int i = 0; i < 5; i++)
			if(!n.contains(" " + numbers[i] + " "))
				Backtrack(n + " " + numbers[i] + " ", k + 1);
		
	}
	
	public static void Backtrack(String n, String o, int k) {
		if(k == 4) {
			if(check(n, o)) {
				flag = true;
			}
			return;
		}
		
		for(int i = 0; i < 3; i++)
			Backtrack(n, o + "" + op[i], k + 1);
	}
	
	public static boolean check(String n, String o) {
		StringTokenizer st = new StringTokenizer(n);
		int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());
		char i = o.charAt(0), j = o.charAt(1), k = o.charAt(2), l = o.charAt(3);
		return compute(compute(compute(compute(a, b, i), c, j), d, k), e, l) == 23L;
	}
	
	public static long compute(long x, long y, char c) {
		switch(c) {
			case '+':
				return x + y;
			case '-':
				return x - y;
			default:
				return x * y;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			
			for(int i = 0; i < 5; i++)
				numbers[i] = sc.nextInt();
			if(numbers[0] == 0)
				break;
			
			flag = false;
			Backtrack("", 0);
			System.out.println(flag ? "Possible" : "Impossible");
		}
	}
}
*/