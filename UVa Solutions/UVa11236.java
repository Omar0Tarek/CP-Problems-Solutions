import java.io.PrintWriter;

public class UVa11236 {
	
	public static boolean check(double i, double j, double k, double l) {
		return i * j * k * j == i + j + k + l;
	}
	
	public static double fix(double x) {
		double y = Math.floor(x);
		x = y + ((int)((x * 100) - (100 * y))) / 100.0;
		return x;
	}
	
	public static void main(String[] args) {
		PrintWriter pw = new PrintWriter(System.out);
		
		for(double i = 0.01; i < 20.00; i += 0.01) {
			if(i * 4 >= 20)
				break;
			for(double j = i; j < 20.00; j += 0.01) {
				if(j * 3 >= 20)
					break;
				for(double k = j; k < 20.00; k += 0.01) {
					if(k * 2 >= 20.00)
						break;
					double l = fix((i + j + k) / (i * j * k - 1));
					System.out.println(i + " " + j + " " + k);
					if(check(i, j, k, l))
						pw.printf("%.2f %.2f %.2f %.2f\n", i, j, k, l);
				}
			}
		}
		System.out.println(fix(11.5237551565));
		pw.flush();
		pw.close();
		
	}
}
