import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UVa10703{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		PrintWriter pw = new PrintWriter(System.out);
		int[][] points;
		while(true) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken()), h = Integer.parseInt(st.nextToken()), n = Integer.parseInt(st.nextToken());
			if(w + h + n == 0)
				break;
			
			points = new int[w][h];
			
			while(n-->0) {
				st = new StringTokenizer(br.readLine());
				int x1 = Integer.parseInt(st.nextToken()), y1 = Integer.parseInt(st.nextToken()), x2 = Integer.parseInt(st.nextToken()), y2 = Integer.parseInt(st.nextToken());
				
				int smallX = Math.min(x1, x2), bigX = Math.max(x1, x2),smallY = Math.min(y1, y2),bigY = Math.max(y1, y2);
				while(smallX <= bigX) {
					while(smallY <= bigY) {
						points[smallX - 1][smallY - 1] = 1;
						smallY++;
					}
					smallY = Math.min(y1, y2);
					smallX++;
				}
			}
			
			int sum = 0;
			
			for(int i = 0; i < w; i++)
				for(int j = 0; j < h; j++)
					if(points[i][j] == 0)
						sum++;
			pw.println("There " + ((sum == 0) ? ("is no") : ((sum == 1) ? ("is one") : ("are " + sum))) + " empty spots.");
			
			br.readLine();
		}
		
		pw.flush();
		pw.close();
	}
}
