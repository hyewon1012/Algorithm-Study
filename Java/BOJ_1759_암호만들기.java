package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759_암호만들기 {
	static int L,C;
	static char[] password;
	static char[] result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken()); // 암호 길이
		C = Integer.parseInt(st.nextToken()); // 암호 종류
		
		//String[] str = br.readLine().split(" ");
		st = new StringTokenizer(br.readLine());
		password = new char[C];
		result = new char[C];
		for (int i = 0; i < C; i++) {
			//password[i] = str[i].charAt(0);
			password[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(password);
		solve(0,0,0,0);
	}

	private static void solve(int v, int o, int start, int cnt) {
		if(cnt == L && v >= 1 && o >= 2) {
			for (int i = 0; i < L; i++) {
				System.out.print(result[i]);
			}
			System.out.println();
			return;
		}
		
		for (int i = start; i < C; i++) {
			result[cnt] = password[i];
			if(password[i] == 'a' || password[i] == 'e' || password[i] == 'i' || password[i] == 'o' || password[i] == 'u') {
				solve(v+1, o, i+1, cnt+1);
			}else {
				solve(v, o+1, i+1, cnt+1);
			}
		}
	}

}
