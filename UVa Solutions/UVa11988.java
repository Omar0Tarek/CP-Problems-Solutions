import java.util.Stack;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UVa11988{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		LinkedList<Character> list;
		Stack<Character> stack = new Stack<>();
		while(br.ready()) {
			list = new LinkedList<>();
			String stringEnter = br.readLine();
			
			for(int i = 0; i < stringEnter.length(); i++) {
				char currentChar = stringEnter.charAt(i);
				if(currentChar == '[') {
					i++;
					while(i < stringEnter.length() && stringEnter.charAt(i) != '[' && stringEnter.charAt(i) != ']') {
						stack.push(stringEnter.charAt(i));
						i++;
					}
					while(!stack.isEmpty())
						list.addLast(stack.pop());
					i--;
				}
				else if(currentChar == ']')
					continue;
				else
					list.addFirst(currentChar);
			}
			while(!list.isEmpty())
				pw.print(list.removeLast());
			pw.print("\n");
		}
		pw.flush();
		pw.close();
	}
}
