package com.ssafy.off;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWEA_1225 {
	static int[] password;
	
	private static void solve(int[] password) {
		Queue<Integer> q = new LinkedList<>();
		
		for (Integer item : password) {
			q.add(item);
		}
		
		while(true) {
			//한사이클 1~5감소
			for (int i = 1; i <= 5; i++) {
				int head = q.poll();
				if(head-i <= 0) {
					q.add(0);
					for (Integer ele : q) {
						System.out.print(ele+" ");
					}
					return;
				}else {
					q.add(head-i);
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		//8개의 숫자를 1~5까지 감소시키는게 한 사이클
		//0보다작아질때 0으로 유지하고 종료 -> 이때 8자리 암호
		Scanner sc = new Scanner(System.in);
		for(int t = 0; t < 10; t++) {
			int tc = sc.nextInt();
			password = new int[8];
			for (int i = 0; i < 8; i++) {
				password[i] = sc.nextInt();
			}
			
			System.out.print("#" + tc+" ");
			solve(password);
			System.out.println();
			
		}
		
		
	}

}
