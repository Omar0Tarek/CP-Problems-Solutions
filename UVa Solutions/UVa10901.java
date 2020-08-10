import java.util.Queue;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UVa10901{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		PrintWriter pw = new PrintWriter(System.out);
		
		int c = Integer.parseInt(br.readLine());
		while(c-->0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()), t = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
			
			Queue<Car> left = new LinkedList<>();
			Queue<Car> right = new LinkedList<>();
			int i = 0;
			while(i++<m) {
				st = new StringTokenizer(br.readLine());
				int store = Integer.parseInt(st.nextToken());
				if(st.nextToken().equals("left"))
					left.add(new Car(store, i - 1));
				else
					right.add(new Car(store, i - 1));
			}
			
			
			int time = 0;
			int state = 1;
			int[] answer = new int[m];
			
			while(!left.isEmpty() || !right.isEmpty()) {
				int timeNew;
				if(!left.isEmpty() && !right.isEmpty()) {
					timeNew = Math.min(right.peek().value, left.peek().value);
				} else {
					timeNew = !left.isEmpty() ? left.peek().value : right.peek().value;
				}
				
				time = timeNew > time ? timeNew : time;
				
				if(state == 1) {
					if(!left.isEmpty() && left.peek().value <= time) {
						int counter = 0;
						while(counter++ < n && !left.isEmpty() && left.peek().value <= time) {
							answer[left.poll().id] = time + t;
						}
						time += t;
						state = 0;
					} else if(!right.isEmpty() && right.peek().value <= time) {
						time += t;
						state = 0;
					}
				} else {
					if(!right.isEmpty() && right.peek().value <= time) {
						int counter = 0;
						while(counter++ < n && !right.isEmpty() && right.peek().value <= time) {
							answer[right.poll().id] = time + t;
						}
						time += t;
						state = 1;
					} else if(!left.isEmpty() && left.peek().value <= time) {
						time += t;
						state = 1;
					}
				}
			}
			
			
			for(int j = 0; j < answer.length; j++)
				pw.println(answer[j]);
			
			pw.print(c == 0 ? "" : "\n");
		}
		
		pw.flush();
		pw.close();
	}
	
	
	public static class Car{
		private int value, id;
		public Car(int value, int id){
			this.value = value;
			this.id = id;
		}
	}
}
