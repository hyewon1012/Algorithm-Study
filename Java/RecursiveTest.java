package com.ssafy.off;

public class RecursiveTest {
	static void recur(int start, int end, int step) {
		//1. 종료파트
		if(start > end) {
			System.out.println();
			return;
		}
		//2. 반복파트
		System.out.print(start + " ");
		recur(start+step, end, step);
		
	}
	private static int total = 0;
	static void sum(int start, int end) {
		//1. 종료파트
		if (start > end) {
			System.out.println("합:" + total);
		}
		else {
			// 반복파트 - 누적, 재귀호출
			total += start;
			sum(start+1, end);
		}

	}
	
	public static void main(String[] args) {
		//1. recur() : start, end , step 반복문처럼 한줄에 출력해준다.
		recur(1,10,2);
		//2. sum() : start,end. start~end 까지의 합을 구하는 함수
		sum(1,10);
	}

}
