import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class UVa10533 {
	
	static ArrayList<Integer> primes;
	static boolean[] isPrime;
	static int[] digitPrime;
	
	public static void sieve(int n) {
		primes = new ArrayList<Integer>();
		isPrime = new boolean[n + 1];
		
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		
		for(int i = 2; i <= n; i++)
			if(isPrime[i]) {
				if(1l * i * i <= n)
					for(int j = i * i; j <= n; j += i)
						isPrime[j] = false;
				primes.add(i);
			}
	}
	
	public static void process() {
		for(int i = 0; i < primes.size(); i++)
			if(isPrime[sum(primes.get(i))])
				digitPrime[i + 1] = 1;
	}
	
	public static int sum(int n) {
		int sum = 0;
		while(n > 0) {
			sum += n % 10;
			n /= 10;
		}
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		
		
		sieve(1000000 - 1);
		int n = primes.size();
		int N = 1;
		while(N < n)
			N <<= 1;
		digitPrime = new int[N + 1];
		process();
		
		SegmentTree tree = new SegmentTree(digitPrime);
		
		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken());
			int i = Collections.binarySearch(primes, l);
			if(i < 0)
				i = -(i + 1);
			int j = Collections.binarySearch(primes, r);
			if(j < 0)
				j = -(j + 1) - 1;
			
			pw.println(tree.query(i + 1, j + 1));
			
		}
		
		pw.flush();
		pw.close();
	}
	
	
	public static class SegmentTree {
		
		int N;
		int[] array, sTree, lazy;
		
		public SegmentTree(int[] in) {
			N = in.length - 1;
			array = in;
			sTree = new int[N<<1];
			lazy = new int[N<<1];
			build(1, 1, N);
		}
		
		public void build(int node, int b, int e) {
			if(b == e) {
				sTree[node] = array[b];
			} else {
				int mid = (b + e) >> 1;
				int leftChild = node << 1;
				int rightChild = node << 1 | 1;
				build(leftChild, b, mid);
				build(rightChild, mid + 1, e);
				sTree[node] = sTree[leftChild] + sTree[rightChild];
			}
		}
		
		public void propogate(int node, int b, int e) {
			int mid = (b + e) >> 1;
			int leftChild = node << 1;
			int rightChild = node << 1 | 1;
			sTree[leftChild] += lazy[node] * (mid - b + 1);
			sTree[rightChild] += lazy[node] * (e - mid);
			lazy[leftChild] += lazy[node];
			lazy[leftChild] += lazy[node];
			lazy[node] = 0;
		}
		
		public int query(int i, int j) {
			return query(1, 1, N, i, j);
		}
		
		public int query(int node, int b, int e, int i, int j) {
			if(b > j || e < i)
				return 0;
			if(b >= i && e <= j)
				return sTree[node];
			
			propogate(node, b, e);
			
			int mid = (b + e) >> 1;
			
			return query(node << 1, b, mid, i, j) + query(node << 1 | 1, mid + 1, e, i, j);
		}
		
		public void update_point(int i, int v) {
			i += N - 1;
			sTree[i] += v;
			while(i > 1) {
				i >>= 1;
				sTree[i] = sTree[i << 1] + sTree[i << 1 | 1];
			}
		}
		
		public void update_range(int i, int j, int v) {
			update_range(1, 1, N, i, j, v);
		}
		
		public void update_range(int node, int b, int e, int i, int j, int v) {
			if(b > j || e < i)
				return;
			if(b >= i && e <= j) {
				lazy[node] += v;
				sTree[node] += (e - b + 1) * v;
				return;
			}
			
			propogate(node, b, e);
			
			int mid = (b + e) >> 1;
			int leftChild = node << 1;
			int rightChild = node << 1 | 1;
			
			update_range(leftChild, b, mid, i, j, v);
			update_range(rightChild, mid + 1, e, i, j, v);
			
			sTree[node] = sTree[leftChild] + sTree[rightChild];
		}
	}

}
