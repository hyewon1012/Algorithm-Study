package com.ssafy.off;

import java.util.Scanner;
import java.util.Stack;

public class SWEA_1218 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int TC = 1; TC <= 10; TC++) {
			int result = 1;
			Stack<Character> stack = new Stack<>();
			int N = sc.nextInt();
			char[] tmp = new char[N];

			tmp = sc.next().toCharArray();
			for (int i = 0; i < N; i++) {
				if (tmp[i] == '[' || tmp[i] == '(' || tmp[i] == '<' || tmp[i] == '{') {
					stack.add(tmp[i]);
				} else {
					if(stack.isEmpty()) {
						result = 0;
						break;
					}
					if (!stack.isEmpty() && stack.peek() == tmp[i]) {
						stack.pop();
					}
				}
			}

			if (!stack.isEmpty()) {
				result = 0;
			}
			System.out.println("#" + TC + " " + result);
		}
	}
}