package com.ssafy.off;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_6808_규영이와인영이의카드게임 {
/**
 * 규영이 카드 입력받음.(18장에서 규영이 카드 뺀게 인영이카드) 
 * 이때 인영이가 카드의 순열을 어떻게 하느냐에 따라서 규영이가 이기는 경우, 지는 경우 몇가지?
 * @throws IOException 
 * @throws NumberFormatException 
 * */
	static int[] A; //규영
	static int[] B; //인영
	static boolean used[]; // 사용 카드 체크
	static boolean isSelected[]; // 순열 생성시 사용 여부 체크
	static int[] result;
	static int win;
	static int lose;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TC; t++) {
			A = new int[9];
			B = new int[9];
			used = new boolean[19];
			isSelected = new boolean[9];
			result = new int[9];
			win = 0;
			lose = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 9; i++) {
				A[i] = Integer.parseInt(st.nextToken());
				used[A[i]] = true;
			}
			 
			int j=0;
			for (int i = 1; i < 19; i++) {
				if(used[i]) continue;
				B[j++] = i; // 인영이 카드 정보 설정 
			}
			//인영이 순열 생성
			permutation(0);
			
			sb.append("#").append(t).append(" ").append(win).append(" ").append(lose).append("\n");
		} // end of test case
		System.out.print(sb);
	}// end of main

	private static void permutation(int cnt) {
		if(cnt == 9) {
			//System.out.println(Arrays.toString(result));
			//구현
			int sumA = 0, sumB = 0;
			for (int i = 0; i < 9; i++) {
				//A가 이기는 경우
				if(A[i] > result[i]) sumA += A[i] + result[i];
				//A가 지는 경우
				if(A[i] < result[i]) sumB += A[i] + result[i];
			}
			if (sumA > sumB) {
				win++;
			}else {
				lose++;
			}
			return;
		}
		for (int i = 0; i < 9; i++) {
			if(isSelected[i]) continue;
			result[cnt] = B[i];
			isSelected[i] = true;
			permutation(cnt+1);
			isSelected[i] = false;
		}
	}

}
