import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Queue;
import java.util.LinkedList;

public class UVa439 {
	
	static int xs, ys, xt, yt;
	static boolean[][] vis;
	static int[] dx = {-1, -1, 1, 1, -2, -2, 2, 2};
	static int[] dy = {-2, 2, -2, 2, -1, 1, -1, 1};
	
	public static int bfs() {
		if(xs == xt && ys == yt)
			return 0;
		
		vis = new boolean[8][8];
		int[][] dist = new int[8][8];
		
		Queue<Pair> qu = new LinkedList<>();
		qu.add(new Pair(xs, ys));
		vis[xs][ys] = true;
		
		while(!qu.isEmpty()) {
			Pair p = qu.poll();
			int x = p.x, y = p.y, w = dist[x][y];
			for(int i = 0; i < dx.length; i++) {
				int xn = x + dx[i], yn = y + dy[i];
				if(isValid(xn, yn)) {
					dist[xn][yn] = w + 1;
					if(xn == xt && yn == yt)
						return dist[xn][yn];
					qu.add(new Pair(xn, yn));
					vis[xn][yn] = true;
				}
			}
		}
		
		return -1;
	}
	
	public static boolean isValid(int x, int y) {
		return x > -1 && x < 8 && y > -1 && y < 8 && !vis[x][y];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		while(true) {
			if(!br.ready())
				break;
			
			String s = br.readLine();
			xs = Integer.parseInt(s.charAt(1) + "") - 1; ys = s.charAt(0) - 'a'; xt = Integer.parseInt(s.charAt(4) + "") - 1; yt = s.charAt(3) - 'a';
			pw.printf("To get from %s to %s takes %d knight moves.\n", s.charAt(0) + "" + s.charAt(1), s.charAt(3) + "" + s.charAt(4), bfs());
		}
		
		pw.flush();
		pw.close();
	}
	
	public static class Pair {
		int x, y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
