import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;

public class UVa978 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			st = new StringTokenizer(br.readLine());
			int B = Integer.parseInt(st.nextToken()), AG = Integer.parseInt(st.nextToken()), AB = Integer.parseInt(st.nextToken());
			
			PriorityQueue<Integer> quB = new PriorityQueue<>(Collections.reverseOrder());
			PriorityQueue<Integer> quG = new PriorityQueue<>(Collections.reverseOrder());
			
			while(AG-->0)
				quG.add(Integer.parseInt(br.readLine()));
			while(AB-->0)
				quB.add(Integer.parseInt(br.readLine()));
			
			while(true) {
				if(quB.isEmpty() || quG.isEmpty())
					break;
				
				ArrayList<Integer> RG = new ArrayList<>(), RB = new ArrayList<>();
				int size = Math.min(B, Math.min(quB.size(), quG.size()));
				for(int i = 0; i < size; i++) {
					int blue = quB.poll(), green = quG.poll();
					if(blue == green)
						continue;
					else
						if(blue > green)
							RB.add(blue - green);
						else
							RG.add(green - blue);
				}
				
				for(int i : RG)
					quG.add(i);
				for(int i : RB)
					quB.add(i);
			}
			
			if(quB.isEmpty() && quG.isEmpty())
				pw.println("green and blue died");
			else {
				if(!quB.isEmpty()) {
					pw.println("blue wins");
					while(!quB.isEmpty())
						pw.println(quB.poll());
				} else {
					pw.println("green wins");
					while(!quG.isEmpty())
						pw.println(quG.poll());
				}
			}
			
			pw.print(t == 0 ? "" : "\n");
		}
		
		pw.flush();
		pw.close();
		
	}
}
