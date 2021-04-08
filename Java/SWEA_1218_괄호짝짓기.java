package com.ssafy.off;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class SWEA_1218_°ýÈ£Â¦Áþ±â {

	static char[] arr;
	static HashMap<Character,Character> bracket;
	
	private static boolean solve() {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++) {
			//¿­¸° °ýÈ£ÀÏ °æ¿ì
			if(arr[i] == '(' || arr[i] == '{' || arr[i] == '[' || arr[i] == '<') {
				stack.push(arr[i]);
			}
			//´Ý´Â °ýÈ£ÀÏ °æ¿ì
			else {
				if(stack.isEmpty()) return false;
				
				if(bracket.get(stack.pop()) != arr[i]) {
					return false;
				}
			}
		}
		
		return stack.isEmpty() ? true : false;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		bracket = new HashMap<>();
		bracket.put('(', ')');
		bracket.put('{', '}');
		bracket.put('[', ']');
		bracket.put('<', '>');
		
		for (int t = 1; t <= 10; t++) {
			int size = sc.nextInt();
			arr = new char[size];
			String temp = sc.next();
			arr = temp.toCharArray();
			
			//solve
			int answer = solve() ? 1 : 0;
			System.out.println("#"+t+" " + answer);

		}
	}

}
