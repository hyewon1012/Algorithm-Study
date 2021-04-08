package com.ssafy.off;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_6808_Retry {
/**
 * 규영이 카드 입력받음.(18장에서 규영이 카드 뺀게 인영이카드) 
 * 이때 인영이가 카드의 순열을 어떻게 하느냐에 따라서 규영이가 이기는 경우, 지는 경우 몇가지?
 * @throws IOException 
 * @throws NumberFormatException 
 * */
	static int[] A; //규영
	static int[] B; //인영
	static boolean used[]; // 사용 카드 체크
	static int win;
	static int lose;
	static int N=9;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TC; t++) {
			A = new int[9];
			B = new int[9];
			used = new boolean[19];

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
			Arrays.sort(B);
			win = 0;
			lose = 0;
			do {
				//구현
				int sumA = 0, sumB = 0;
				for (int i = 0; i < 9; i++) {
					//A가 이기는 경우
					if(A[i] > B[i]) sumA += A[i] + B[i];
					//A가 지는 경우
					if(A[i] < B[i]) sumB += A[i] + B[i];
				}
				if (sumA > sumB) {
					win++;
				}else {
					lose++;
				}
			}while(np());

			sb.append("#").append(t).append(" ").append(win).append(" ").append(lose).append("\n");
		} // end of test case
		System.out.print(sb);
	}// end of main
	
	public static boolean np() {
		int i = N-1;
		while(i > 0 && B[i-1] >= B[i]) --i;
		if(i==0) return false;
		
		int j = N-1;
		while(B[i-1] >= B[j]) --j;
		swap(i-1,j);
		
		int k = N-1;
		while(i < k) {
			swap(i++, k--);
		}
		return true;
	}

	private static void swap(int i, int j) {
		int temp = B[i];
		B[i] = B[j];
		B[j] = temp;
	}

}
