import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.Queue;
import java.util.LinkedList;

public class UVa469 {
	
	static char[][] grid;
	static boolean[][] vis;
	static int X = 0, Y = 0;
	
	static int[] dx = {0, 1, 0, -1, -1, 1, -1, 1};
	static int[] dy = {-1, 0, 1, 0, -1, 1, 1, -1};
	
	public static int floodfill(int x, int y) {
		vis[x][y] = true;
		int count = 0;
		for(int i = 0; i < dx.length; i++) {
			if(isValid(x + dx[i], y + dy[i])) {
				count += 1 + floodfill(x + dx[i], y + dy[i]);
			}
		}
		return count;
	}
	
	public static boolean isValid(int x, int y) {
		return x < X && x > -1 && y < Y && y > -1 && !vis[x][y] && grid[x][y] == 'W';
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		br.readLine();
		
		while(t-->0) {
			Queue<String> qu = new LinkedList<>();
			String enter = "";
			while(true) {
				enter = br.readLine();
				if(enter.charAt(0) >= '0' && enter.charAt(0) <= '9')
					break;
				qu.add(enter);
			}
			
			Queue<String> q = new LinkedList<>();
			q.add(enter);
			while(br.ready()) {
				enter = br.readLine();
				if(enter.equals(""))
					break;
				q.add(enter);
			}
			
			X = qu.size();
			Y = qu.peek().length();
			
			grid = new char[X][Y];
			for(int i = 0; i < X; i++) {
				String curr = qu.poll();
				for(int j = 0; j < Y; j++)
					grid[i][j] = curr.charAt(j);
			}
			
			while(!q.isEmpty()) {
				st = new StringTokenizer(q.poll());
				int x = Integer.parseInt(st.nextToken()) - 1, y = Integer.parseInt(st.nextToken()) - 1;
				vis = new boolean[X][Y];
				System.out.println(1 + floodfill(x, y));
			}
			
			System.out.print(t == 0 ? "" : "\n");
		}
	}
}