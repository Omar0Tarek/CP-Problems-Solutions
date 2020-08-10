import java.util.Stack;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Collections;
import java.util.LinkedList;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UVa11995 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		Stack<Integer> stack;
		PriorityQueue<Integer> pqu; 
		Queue<Integer> qu;
		
		while(br.ready()) {
			int n = Integer.parseInt(br.readLine());
			
			stack = new Stack<>();
			pqu = new PriorityQueue<>(Collections.reverseOrder());
			qu = new LinkedList<>();
			
			boolean stackFlag = true, quFlag = true, pquFlag = true;
			for(int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				if(Integer.parseInt(st.nextToken()) == 1) {
					int add = Integer.parseInt(st.nextToken());
					stack.add(add);
					pqu.add(add);
					qu.add(add);
				} else {
					int compare = Integer.parseInt(st.nextToken());
					if(stack.isEmpty() || stack.pop() != compare)
						stackFlag = false;
					if(pqu.isEmpty() || pqu.poll() != compare)
						pquFlag = false;
					if(qu.isEmpty() || qu.poll() != compare)
						quFlag = false;
				}
			}
			
			
			String out = "impossible";
			if(stackFlag) {
				out = "stack";
				if(pquFlag || quFlag)
					out = "not sure";
			} else if (pquFlag) {
				out = "priority queue";
				if(quFlag)
					out = "not sure";
			} else if(quFlag) {
				out = "queue";
			}
			
			pw.println(out);
		}
		
		pw.flush();
		pw.close();
	}
}
