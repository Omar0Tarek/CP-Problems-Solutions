import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.Stack;
import java.util.TreeMap;

public class UVa536 {
	
	static PrintWriter pw = new PrintWriter(System.out);
	static int n;
	static char[] pre;
	static int[] left, right;
	static TreeMap<Character, Integer> map;
	
	
	public static void constructTree() {
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		
		for(int i = 1; i < n; i++) {
			int x = stack.peek();
			if(map.get(pre[x]) > map.get(pre[i])) {
				left[x] = i;
				stack.push(i);
			} else {
				while(!stack.isEmpty() && map.get(pre[i]) > map.get(pre[stack.peek()]))
					x = stack.pop();
				right[x] = i;
				stack.push(i);
			}
		}
	}
	
	public static void postOrder(int u) {
		if(left[u] != 0)
			postOrder(left[u]);
		if(right[u] != 0)
			postOrder(right[u]);
		pw.print(pre[u] + "");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			if(!br.ready())
				break;
			
			st = new StringTokenizer(br.readLine());
			pre = st.nextToken().toCharArray();
			n = pre.length;
			
			String in = st.nextToken();
			map = new TreeMap<>();
			for(int i = 0; i < n; i++)
				map.put(in.charAt(i), i + 1);
			
			left = new int[n];
			right = new int[n];
			
			constructTree();
			postOrder(0);
			pw.print("\n");
		}
		
		pw.flush();
		pw.close();
	}
}
