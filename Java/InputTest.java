package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class InputTest {
	static String str = "1011111100\n";
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new StringReader(str));
		
		
		//String line = br.readLine();
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		char[] charArr = new char[10];
//		for (int i = 0; i < 10; i++) {
//			//charArr[i] = line.charAt(i);
//			charArr[i] = st.nextToken().charAt(0);
//			
//		}
		//System.out.println(Arrays.toString(charArr));
		
		List<Integer> sortNumbers = new ArrayList<Integer>();
		sortNumbers.add(1);
		sortNumbers.add(5);
		sortNumbers.add(2);
		sortNumbers.add(7);
		Collections.sort(sortNumbers, Collections.reverseOrder());
		
		Integer[] sortInteger = {1,5,2,7};
		Arrays.sort(sortInteger,Collections.reverseOrder());
		for (Integer integer : sortInteger) {
			System.out.print(integer+" ");
		}
	}

}
