package com.ssafy.off;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//탐욕 기법 Greedy 알고리즘
//번뜩이는 아이디어가 좀 필요하다 연습을 통해 극복하자
/**
 * sum : 잘려진 조각의 총개수 누적
 * cnt : 나와있는 쇠막대기 개수
 * 
 * () 레이저	: sum += cnt
 * (		: cnt++
 * )		: sum++, cnt--
 */
public class SWEA_5432 {
	//cnt는 현재 쇳조각개수로 -1 +1 이렇게 변화한다 <- 닫힌괄호면 cnt-1 sum+1
	//sum 은 누적 쇠조각
	//레이저가 나오면 sum+=cnt
	static char[] arr;
	static Stack<Character> stack;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			String s = br.readLine(); // ()()()() 이런 문자열
			s = s.replace("()", "v"); // 아니 교수님 천재...
			//replace해도 string은 원본 배열이 바뀌지않는다
			
			int sum = 0; // 잘려진 조각의 총개수 누적
			int cnt = 0; // 나와있는 쇠막대기 개수
			//(((v)))(v)v () => v
			for (int i = 0; i < s.length(); i++) {
				if(s.charAt(i) == 'v') { // v 레이저
					sum += cnt; // 쇠막대기만큼 누적
				}else if(s.charAt(i) == '(') { // 시작괄호
					cnt++; // 쇠막대기 추가
				}else { // 닫는 괄호
					sum++; //쇠막대기 1개 마감됨, 개수 추가
					cnt--; // 쇠막대기 개수 1개 줄어듦
				}
			}
			sb.append("#").append(testCase).append(" ").append(sum).append("\n");			
		}
		System.out.print(sb);
	}

}
