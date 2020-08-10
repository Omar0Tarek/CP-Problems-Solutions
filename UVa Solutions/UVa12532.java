import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa12532 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		while(true) {
			if(!br.ready())
				break;
			
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
			int N = 1;  
			while(N < n) N <<= 1;
			
			int[] array = new int[N + 1];
			Arrays.fill(array, 1);
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= n; i++) {
				int x = Integer.parseInt(st.nextToken());
				array[i] = x > 0 ? 1 : (x < 0 ? -1 : 0);
			}
			SegmentTree tree = new SegmentTree(array);
			
			while(k-->0) {
				st = new StringTokenizer(br.readLine());
				if(st.nextToken().equals("C")) {
					int i = Integer.parseInt(st.nextToken()), x = Integer.parseInt(st.nextToken());
					tree.update_point(i, x > 0 ? 1 : (x < 0 ? -1 : 0));
				} else {
					int p = tree.query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
					pw.print(p > 0 ? '+' : (p == 0 ? '0' : '-'));
				}
			}
			
			pw.print("\n");
		}
		
		pw.flush();
		pw.close();
		
	}
	
	public static class SegmentTree {
		
		int N;
		int[] array, sTree;
		
		public SegmentTree(int[] in) {
			N = in.length - 1;
			array = in;
			sTree = new int[N<<1];
			build(1, 1, N);
		}
		
		public void build(int node, int b, int e) {
			if(b == e) {
				sTree[node] = array[b];
			} else {
				int mid = (b + e) >> 1;
				int leftChild = node << 1;
				int rightChild = (node << 1) | 1;
				build(leftChild, b, mid);
				build(rightChild, mid + 1, e);
				sTree[node] = sTree[leftChild] * sTree[rightChild];
	 		}
		}
		
		public void update_point(int i, int v) {
			i += N - 1;
			sTree[i] = v;
			while(i > 1) {
				i >>= 1;
				sTree[i] = sTree[i << 1] * sTree[i << 1 | 1];
			}
		}
		
		public int query(int i, int j) {
			return query(1, 1, N, i, j);
		}
		
		public int query(int node, int b, int e, int i, int j) {
			if(b > j || e < i)
				return 1;
			if(b >= i && e <= j)
				return sTree[node];
						
			int mid = (b + e) >> 1;
			return query(node << 1, b, mid, i, j) * query((node << 1) | 1, mid + 1, e, i, j);
		}
		
	}

}