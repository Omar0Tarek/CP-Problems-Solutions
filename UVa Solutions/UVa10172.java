import java.util.*;
public class UVa10172 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int sets = sc.nextInt();
		for(int set = 0; set < sets; set++) {
			long time = -2L;
			int n = sc.nextInt(), stackMax = sc.nextInt(), quMax = sc.nextInt();
			
			Queue<Integer>[] quArray = new Queue[n];
			for(int arrayCounter = 0; arrayCounter < n; arrayCounter++) {
				quArray[arrayCounter] = new LinkedList<Integer>();
				int quSize = sc.nextInt();
				for(int quCounter = 0; quCounter < quSize; quCounter++) {
					quArray[arrayCounter].add(sc.nextInt());
				}
			}
			
			Stack<Integer> stack = new Stack<>();
			
			int counter = 1;
			while(!(areQueuesEmpty(quArray) && stack.isEmpty())) {
				time += 2L;
				
				while(!stack.isEmpty()) {
					if(stack.peek() == counter)
						stack.pop();
					else {
						if(quArray[counter - 1].size() == quMax) {
							break;
						} else {
							quArray[counter - 1].add(stack.pop());
						}
					}
					time++;
				}
				
				while(stack.size() < stackMax && !quArray[counter - 1].isEmpty()) {
					stack.push(quArray[counter - 1].poll());
					time++;
				}
				
				counter = (counter == n) ? 1 : (++counter);
				
			}
			
			
			System.out.println(time != -2 ? time : 0);
		}
	}
	
	public static boolean areQueuesEmpty(Queue<Integer>[] quArray) {
		for(int i = 0; i < quArray.length; i++) {
			if(!quArray[i].isEmpty())
				return false;
		}
		return true;
	}
}
