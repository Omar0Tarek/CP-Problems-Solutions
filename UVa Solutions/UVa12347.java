import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class UVa12347 {
	
	static PrintWriter pw = new PrintWriter(System.out);
	static ArrayList<Integer> nodes;
	static int[] left, right;
	
	public static void constructTree() {
		Stack<Integer> stack = new Stack<Integer>();
		stack.add(0);
		
		for(int i = 1, n = nodes.size(); i < n; i++) {
			int x = stack.peek();
			if(nodes.get(x) > nodes.get(i)) {
				left[x] = i;
				stack.push(i);
			} else {
				while(!stack.isEmpty() && nodes.get(i) > nodes.get(stack.peek()))
					x = stack.pop();
				right[x] = i;
				stack.push(i);
			}
		}
	}
	
	public static void printPostOrder(int u) {
		if(left[u] != 0)
			printPostOrder(left[u]);
		if(right[u] != 0)
			printPostOrder(right[u]);
		pw.println(nodes.get(u));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		nodes = new ArrayList<Integer>();
		while(true) {
			if(!br.ready())
				break;
			nodes.add(Integer.parseInt(br.readLine()));
		}
		
		right = new int[nodes.size()];
		left = new int[nodes.size()];
		
		constructTree();
		printPostOrder(0);
		pw.flush();
		pw.close();
		
	}
}
