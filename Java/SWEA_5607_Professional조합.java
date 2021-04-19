package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5607_Professional조합 {
	static int N,R;
	static long MOD = 1234567891;
	static long[] factorial;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TC; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			factorial  = new long[N+1];
			
			factorial[0] = 1;
			
			for (int i = 1; i <= N; i++) {
				factorial[i] = (factorial[i-1]*i) % MOD;
			}
			
			//값이 초과할수있기때문에 mod 연산 여기서도 해준다
			long bottom = (factorial[N-R] * factorial[R]) % MOD; //1/a에서 a에 해당하는 값
			bottom = power(bottom, MOD-2);
			
			sb.append("#").append(t).append(" ").append((factorial[N] * bottom)%MOD).append("\n");
		}
		
		System.out.print(sb);
	}
	
	// 분할 정복
	private static long power(long a, long b) { //a의 b승
		if(b == 0) return 1;
		else if(b == 1) return a;
		
		if(b%2 == 0) { // b가 짝수인 경우
			long tmp = power(a, b/2);
			return ((tmp * tmp) % MOD);
		}
		
		long tmp = power(a, b-1) % MOD;
		return (tmp*a) % MOD;

	}

}
