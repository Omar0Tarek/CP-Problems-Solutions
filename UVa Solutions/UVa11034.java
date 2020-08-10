import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class UVa11034 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken()) * 100, m = Integer.parseInt(st.nextToken());
			Queue<Integer> right = new LinkedList<>();
			Queue<Integer> left = new LinkedList<>();
			
			while(m-->0) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				if(st.nextToken().equals("left"))
					left.add(x);
				else
					right.add(x);
			}
			
			int countLeft = 0, countRight = 0;
			int count = 0;
			while(true) {
				if(left.isEmpty()) {
					if(count != 0)
						countLeft++;
					break;
				}
				
				if(count + left.peek() <= l)
					count += left.poll();
				else {
					countLeft++;
					count = 0;
				}
			}
			
			count = 0;
			while(true) {
				if(right.isEmpty()) {
					if(count != 0)
						countRight++;
					break;
				}
				
				if(count + right.peek() <= l)
					count += right.poll();
				else {
					countRight++;
					count = 0;
				}
			}
			
			System.out.println(countRight >= countLeft ? 2 * countRight : (2 * countLeft - 1));
		}
	}
}
