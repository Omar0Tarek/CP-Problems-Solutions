import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UVa594{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		while(br.ready()) {
			String numberEnter = br.readLine();
			int numberOut = decode(Integer.toBinaryString(Integer.parseInt(numberEnter)));
			//System.out.println(Integer.toBinaryString(Integer.parseInt(numberEnter)));
			pw.println(numberEnter + " converts to " + numberOut);
		}
		
		pw.flush();
		pw.close();
	}
	
	public static int decode(String numberEnter) {
		/*
		* while(numberEnter.length() < 32)
		*	numberEnter = "0" + numberEnter;
		*/
		
		int numberOut = 0;
		for(int i = numberEnter.length() - 1; i >= 0; i--)
			numberOut += (int)((numberEnter.charAt(i) - 48) * Math.pow(2, 32 - (numberEnter.length() - i)));
		
		if(numberEnter.charAt(numberEnter.length() - 1) == '1')
			numberOut = 1 * (~numberOut + 1);
		return numberOut;
	}
}
