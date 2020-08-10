import java.util.TreeSet;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UVa11062 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		TreeSet<String> set = new TreeSet<>();
		
		while(br.ready()) {
			String line = br.readLine();
			
			if(line.charAt(line.length() - 1) == '-')
				sb.append(line.substring(0, line.length() - 1));
			else
				sb.append(line + " ");
		}
		
		st = new StringTokenizer(sb.toString());
		while(st.hasMoreTokens()) {
			String word = st.nextToken().toLowerCase();
			
			int i = 0, j = word.length() - 1;
			while(!(word.charAt(i) >= 'a' && word.charAt(i) <= 'z'))
				i++;
			while(!(word.charAt(j) >= 'a' && word.charAt(j) <= 'z'))
				j--;
			
			set.add(word.substring(i, j + 1));
		}
		
		while(!set.isEmpty())
			pw.println(set.pollFirst());
		
		pw.flush();
		pw.close();
	}
}
