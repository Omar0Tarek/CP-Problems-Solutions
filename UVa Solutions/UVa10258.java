import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.StringTokenizer;
import java.util.Arrays;

public class UVa10258 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		br.readLine();
		while(T-->0) {
			
			boolean[] participate = new boolean[100];
			Con[] con = new Con[100];
			for(int i = 0; i < con.length; i++)
				con[i] = new Con(i);
			
			while(true) {
				String s;
				if(!br.ready() || (s = br.readLine()).length() == 0)
					break;
				st = new StringTokenizer(s);
				int c = Integer.parseInt(st.nextToken()), p = Integer.parseInt(st.nextToken()), t = Integer.parseInt(st.nextToken());
				char x = st.nextToken().charAt(0);
				
				participate[c - 1] = true;
				switch(x) {
					case 'C':
						if(con[c - 1].problem[p - 1] == false) {
							con[c - 1].problem[p - 1] = true;
							con[c - 1].number++;
							con[c - 1].time += t + con[c - 1].unsolved[p - 1] * 20;
						}
						break;
						
					case 'I':
						if(con[c - 1].problem[p - 1] == false) {
							con[c - 1].unsolved[p - 1]++;
						}
						break;
				}
			}
			
			Arrays.sort(con);
			for(int i = 0; i < 100; i++)
				if(participate[con[i].ID])
					System.out.println(con[i]);
			System.out.print(T == 0 ? "" : "\n");
		}
	}
	
	public static class Con implements Comparable<Con>{
		boolean[] problem = new boolean[9];
		int[] unsolved = new int[9];
		int time = 0;
		int number = 0;
		int ID = 0;
		
		public Con(int ID) {
			this.ID = ID;
		}
		
		@Override
		public int compareTo(Con c) {
			if(this.number == c.number)
				return this.time - c.time;
			return c.number - this.number;
		}
		
		@Override
		public String toString() {
			return (ID + 1) + " " + number + " " + time;
		}
	}
}