package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class SWEA_1223_계산기2 {
	static Stack<Character> midStack;
	static Stack<Integer> postfixStack;
	//입력값 우선순위가 더 높으면 true
	//낮거나 같으면 false
	public static boolean check(char input, char top) {
		if(input == '*' && top != '*') return true;
		else if(input == '+' && top == '*') return false;
		else return false;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= 10; t++) {
			int N = Integer.parseInt(br.readLine());
			
			
			char[] mid = new char[N];
			char[] result = new char[N];
			midStack = new Stack<>();
			mid = br.readLine().toCharArray();
			int idx = 0;
			//중위표기식 후위표기식으로 변환
			for (int i = 0; i < N; i++) {
				if(mid[i] != '+' && mid[i] != '*') { //피연산자일때
					result[idx++] = mid[i];
					
				}else {
					if(midStack.empty()) {
						midStack.push(mid[i]);
					}else {
						if(!check(mid[i], midStack.peek())) {
							result[idx++] = midStack.pop();
							midStack.push(mid[i]);
						}else {
							midStack.push(mid[i]);
						}	
					}
				}
			}
			while(!midStack.empty()) {
				result[idx++] = midStack.pop();
			}
			
			
			//후위표기식 계산
			postfixStack = new Stack<>();
			for (int i = 0; i < result.length; i++) {
				if(result[i] != '*' && result[i] != '+') {
					postfixStack.push(result[i]-'0');
				}else {
					int r = postfixStack.pop();
					int l = postfixStack.pop();
					if(result[i] == '*') {
						postfixStack.push(r*l);
					}else {
						postfixStack.push(r+l);
					}
				}
			}
			sb.append("#").append(t).append(" ").append(postfixStack.pop()).append("\n");
			
		}
		System.out.print(sb);
	}

}
