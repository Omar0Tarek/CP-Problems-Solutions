import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class UVa11495 {
	
	static int steps = 0;
	
	public static void MergeSort(int[] array, int start, int end) {
		if(start == end)
			return;
		
		int mid = (start + end) / 2;
		MergeSort(array, start, mid);
		MergeSort(array, mid + 1, end);
		
		merge(array, start, mid, end);
	}
	
	public static void merge(int[] array, int start, int mid, int end) {
		int startFirst = start, endFirst = mid, startSecond = mid + 1, endSecond = end;
		int[] tempArray = new int[end - (start - 1)];
		int counter = 0;
		
		while(counter < tempArray.length) {
			if(startFirst > endFirst)
				tempArray[counter++] = array[startSecond++];
			else if(startSecond > endSecond)
				tempArray[counter++] = array[startFirst++];
			else if(array[startFirst] > array[startSecond]) {
				tempArray[counter++] = array[startSecond++];
				steps += mid - (startFirst - 1);
			} else
				tempArray[counter++] = array[startFirst++];
		}
		
		for(int i = 0; i < tempArray.length; i++)
			array[start + i] = tempArray[i];
		
	}
	
	//Bubble Sort will give a TLE verdict,
	//also looping over every element of the array, and calculating the number of elemnts that are after it and lower than it will take a TLE verdict
	/*public static void BubbleSort(int[] array) {
		boolean flag = false;
		int i = 1;
		while(!flag) {
			flag = true;
			for(int j = 0; j < array.length - i; j++) {
				if(array[j + 1] < array[j]) {
					int temp = array[j + 1];
					array[j + 1] = array[j];
					array[j] = temp;
					flag = false;
					steps++;
				}
			}
			i++;
		}
	}*/
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder("");
		
		while(true) {
			String enter = br.readLine();
			if(enter.equals("0"))
				break;
			
			st = new StringTokenizer(enter);
			int n = Integer.parseInt(st.nextToken());
			int[] array = new int[n];
			for(int i = 0; i < array.length; i++)
				array[i] = Integer.parseInt(st.nextToken());
			
			steps = 0;
			MergeSort(array, 0, array.length - 1);
			if(steps % 2 == 0)
				sb.append("Carlos" + "\n");
			else
				sb.append("Marcelo" + "\n");
		}
		
		System.out.print(sb.toString());
	}
}
