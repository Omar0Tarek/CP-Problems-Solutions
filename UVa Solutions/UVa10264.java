import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
public class UVa10264{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		while(br.ready()) {
			int n = Integer.parseInt(br.readLine());
			int[] weights = new int[(int) Math.pow(2, n)];
			for(int i = 0; i < weights.length; i++)
				weights[i] = Integer.parseInt(br.readLine());
			
			int[] potencies = getPotArray(weights, n);
			
			pw.println(getPotMax(potencies, n));
		}
		
		pw.flush();
		pw.close();
	}
	
	public static int[] getPotArray(int[] weights, int n) {
		int[] potencies = new int[weights.length];
		int[] numPotencies;
		
		for(int i = 0; i < weights.length; i++) {
			numPotencies = getPotCorner(i, n);
			for(int j = 0; j < n; j++)
				potencies[i] += weights[numPotencies[j]];
		}
		
		return potencies;
	}
	
	public static int[] getPotCorner(int number, int n) {
		int[] numPotencies = new int[n];
		String binary = Integer.toBinaryString(number);
		
		while(binary.length() < n)
			binary = "0" + binary;
		
		for(int i = 0; i < n; i++)
			numPotencies[i] = (binary.charAt(i) == '1') ? (number - ((int)Math.pow(2, n - (i + 1)))) : (number + ((int)Math.pow(2, n - (i + 1))));
		
		return numPotencies;
	}

	public static int getPotMax(int[] potencies, int n) {
		int max = 0;
		int[] numPotencies;
		
		for(int i = 0; i < potencies.length; i++) {
			numPotencies = getPotCorner(i, n);
			int maxIn = 0;
			
			for(int j = 0; j < n; j++)
				if(potencies[numPotencies[j]] > maxIn)
					maxIn = potencies[numPotencies[j]];
			
			if(potencies[i] + maxIn > max)
				max = potencies[i] + maxIn;
		}
		
		return max;
	}
}
