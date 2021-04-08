package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Baekjoon_1339_단어수학 {
	static int N;
	static Character[] result;
	static List<Character> alphabet;
	static List<String> input;
	static HashMap<Character, Character> dict;
	static int ans = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		alphabet = new ArrayList<Character>();
		result = new Character[10];
		input = new ArrayList<String>();
		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			input.add(temp);
			for (int j = 0; j < temp.length(); j++) {
				//중복 체크
				if(dupicateCheck(temp.charAt(j))) {
					alphabet.add(temp.charAt(j));
				}
				
			}
		}
		//solve
		permutation(0, 0);

		System.out.println(ans);
		

	}
	
	private static boolean dupicateCheck(char c) {
		for (int i = 0; i < alphabet.size(); i++) {
			if(alphabet.get(i) == c) return false;
		}
		return true;
	}

	private static void permutation(int cnt, int flag) {
		if(cnt == alphabet.size()) {
			
			//앞에서부터 차례대로 9,8,7 숫자 생성
			//{알파벳 : 숫자} hashmap
			int sum = 0;
			dict = makeNumber(result);
			for (int i = 0; i < input.size(); i++) {
				char[] temp = new char[input.get(i).length()];
				for (int j = 0; j < input.get(i).length(); j++) {
					//숫자를 문자열로 인식한다음 누적합계산해야한다..
					char c = dict.get(input.get(i).charAt(j));
					temp[j] = c;
				}
				String s = new String(temp);
				sum += Integer.parseInt(s);
				
			}
			
			
			ans = Math.max(ans, sum);
			return;
		}
		for (int i = 0; i < alphabet.size(); i++) {
			if((flag & 1<<i) != 0) continue;
			result[cnt] = alphabet.get(i);
			permutation(cnt+1, flag | (1<<i));
		}
	}

	private static HashMap<Character, Character> makeNumber(Character[] arr) {
		int num = 9;
		HashMap<Character, Character> result = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] == null) continue;
			result.put(arr[i], (char)(num-- + '0'));
		}
		return result;
		
	}

}
