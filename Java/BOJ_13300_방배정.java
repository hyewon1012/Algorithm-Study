package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13300_방배정 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		int N = Integer.parseInt(info[0]); // 학생들 수
		int K = Integer.parseInt(info[1]); // 방에 최대 K 명
		
		//1학년 여자 a명 남자b명 한 방에 최대 K명
		//a/K == 0 ? 1 : a/K
		int[][] students = new int[7][2]; //n학면 0 : 여자수 , n학년 1 : 남자수
		
		//[1:[a,b]] 여자 남자
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int flag = Integer.parseInt(st.nextToken());
			int grade = Integer.parseInt(st.nextToken());
			students[grade][flag] += 1;
		}
		int ans=0;
		for (int i = 1; i < students.length; i++) {
			if(students[i][0] > 0) {
				ans += students[i][0] / K;
				ans += students[i][0] % K == 0 ? 0 : 1;
				
			}
			if(students[i][1] > 0) {
				ans += students[i][1] / K;
				ans += students[i][1] % K == 0 ? 0 : 1;
			}

		}
		System.out.println(ans);
		
	}

}
