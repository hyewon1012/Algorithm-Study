package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 0. while(N-- > 0) -> for (i = N-1; i > 0; i++)
 * 1. 첫번째수:N, 두번째수:N을제외한 양수, arr[i-2]-arr[i-1], ... 
 * 2. 음의 정수 만들어지면 길이 체크
 * 3. 최대 개수가 여러개일때 그중 하나만 출력
 * 4. 1~3번 반복
 * 5. 최대갯수가 갱신되면 최종 결과 출력할 배열에 현재 만들어진 배열 저장. 최대길이값 저장함. -> 반복문 모두 돌고 정답 출력
 * */
public class Baekjoon_2635_수이어가기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Integer> arr = new ArrayList<Integer>();
		List<Integer> result = new ArrayList<Integer>();
		boolean[] isSelected;
		
//		int length = 0;
//		while(N > 0) {
//			arr.add(N);
//			for (int i = N; i >= 0; i--) {
//				arr.add(i);
//
//				//수열 생성 0 1 2(nowIdx)
//				int newIdx = arr.size();
//				
//				int val = arr.get(newIdx-2)-arr.get(newIdx-1);
//				if(val < 0) {
//					if(length < arr.size()) {
//						for (Integer a : arr) {
//							System.out.print(a+" ");
//						}
//						System.out.println();
//						length = arr.size();
//						result.addAll(arr);
//						arr.clear();
//					}
//					break;
//				}else {
//					arr.add(val);
//				}	
//			}
//			for (Integer a : arr) {
//				System.out.print(a+" ");
//			}
//			N--;
//		}
//		System.out.println(length);
		solve(N);
		
	}

	private static void solve(int N) {
		int maxLength = 0; // 수열 길이 최대값
		String maxResult = ""; // 최대 수열을 저장할 문자열
		for (int i = 1; i <= N; i++) {
			int x = N; //첫번째 숫자
			int y = i; //두번째 숫자
			int temp; //세번째 숫자
			
			StringBuilder result = new StringBuilder(x+" "+y); // 출력할 수열을 문자열로 저장
			int length = 2; // 수열의 길이
			while(true) { // 수열 생성
				temp = x-y;
				if(temp < 0) { // 음수가 되면 종료
					if(maxLength < length) {
						maxLength = length;
						maxResult = result.toString();
					}
					break;
				}
				result.append(" ").append(temp);
				length++;				
				x = y;
				y = temp;
			}// end of while
		}
		System.out.println(maxLength);
		System.out.println(maxResult);
		
	}
	

}
