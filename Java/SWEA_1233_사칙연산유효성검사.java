package com.ssafy.off;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1233_사칙연산유효성검사 {

	static String[] tree;
	static int N;
	/**
	 * 1. 정점이 연산자일때
	 * 	1-1. 연산자 + 연산자
	 * 	1-2. 연산자 + 숫자
	 * 	1-3. 숫자 + 숫자
	 * 2. 정점이 숫자일때 리프노드여야함.
	 * */
	static char[] operatorTree;
	
	private static int dfs(int idx) {
		//리프 노드일때 연산자 아니면 true
		if(idx*2 > N || tree[idx*2] == null) {
			if(!tree[idx].equals("*") && !tree[idx].equals("/") && !tree[idx].equals("-") && !tree[idx].equals("+") && !tree[idx].equals(null)) {
				return 1;
			}else {
				return 0;
			}
			
		}
		//리프 아닌데 숫자면 false
		else if(!tree[idx].equals("*") && !tree[idx].equals("/") && !tree[idx].equals("-") && !tree[idx].equals("+") && !tree[idx].equals(null)) {
			return 0;
		}
		else {
			int left = dfs(idx*2);
			int right = dfs(idx*2+1);
			if(left==1 && right==1) {
				return 1;
			}
		}
		return 0;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		final int TC = 10;
		for (int t = 1; t <= TC; t++) {
			N = Integer.parseInt(br.readLine());
			operatorTree = new char[N+1];
			boolean flag = true;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int index = Integer.parseInt(st.nextToken()); // 정점 번호
				operatorTree[index] = st.nextToken().charAt(0); // 정점이 가지는 값
			}
			int ans = 1;
			//모든 정점에 대해 조사
			int left, right;
			for (int j = 1; j <= N; j++) {
				left = j*2;
				right = j*2+1;
				//1. 정점이 연산자인 경우
				if(!Character.isDigit(operatorTree[j])) {
					if(left <= N && right <= N) {
						if(Character.isDigit(operatorTree[left]) && !Character.isDigit(operatorTree[right])) {
							flag = false;
						}
					}else {//자식이 없으면
						flag = false;
					}
				}else { //2. 정점이 숫자인 경우 -> 자식이 있으면 안됨
					if(left <= N ) {
						flag = false;
					}
				}
				
			}
			ans = flag ? 1 : 0;
//			tree = new String[N+1];
//			int ans = 1;
//			for (int i = 1; i <= N; i++) {
//				String[] s = br.readLine().split(" ");
//				tree[i] = s[1];
//			}
//			//solve
//			ans = dfs(1);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
			
		}// end of test case
		System.out.print(sb);
	}// end of main

}// end of class
