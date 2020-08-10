import java.util.Stack;
import java.util.Iterator;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class UVa00101 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		Stack<Integer>[] stacks = new Stack[n];
		for(int i = 0; i < n; i++) {
			stacks[i] = new Stack<>();
			stacks[i].add(i);
		}
		
		while(true) {
			String[] command = br.readLine().split(" ");
			
			if(command[0].equals("quit"))
				break;
			
			if(command[1].equals(command[3]))
				continue;
			
			switch(command[0] + " " + command[2]) {
			
			 case "move over":
				 while(stacks[Integer.parseInt(command[1])].size() > 1)
					 stacks[stacks[Integer.parseInt(command[1])].peek()].add(stacks[Integer.parseInt(command[1])].pop());
			 case "pile over":
				  Iterator<Integer> value = stacks[Integer.parseInt(command[1])].iterator();
				  while(value.hasNext())
					  stacks[Integer.parseInt(command[3])].add(value.next());
				  while(stacks[Integer.parseInt(command[1])].size() > 1)
						 stacks[Integer.parseInt(command[1])].pop();
				 break;
			 case "move onto":
				 while(stacks[Integer.parseInt(command[3])].size() > 1)
					 stacks[stacks[Integer.parseInt(command[3])].peek()].add(stacks[Integer.parseInt(command[3])].pop());
			 case "pile onto":
				 while(stacks[Integer.parseInt(command[1])].size() > 1)
					 stacks[stacks[Integer.parseInt(command[1])].peek()].add(stacks[Integer.parseInt(command[1])].pop());
				 Iterator<Integer> Value = stacks[Integer.parseInt(command[1])].iterator();
				 while(Value.hasNext())
					  stacks[Integer.parseInt(command[3])].add(Value.next());
				 while(stacks[Integer.parseInt(command[1])].size() > 1)
					 stacks[Integer.parseInt(command[1])].pop();
				 break;
				 
			}
		}
		
		for(int i = 0; i < n; i++) {
			System.out.print(i+":");
			Iterator<Integer> value = stacks[i].iterator();
			while(value.hasNext())
				System.out.print(" " + value.next());
			System.out.print("\n");
		}
	}
}
