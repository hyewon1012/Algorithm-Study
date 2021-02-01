package com.baekjoon.algo;

import java.util.Scanner;

public class Switch {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] switchs = new int[n+1];
		for (int i = 1; i <= n; i++) {
			switchs[i] = sc.nextInt();		
		}
		
		int person = sc.nextInt();
		
		for (int i = 0; i < person; i++) {
			int gender = sc.nextInt();
			int value = sc.nextInt();
			
			if(gender == 1) {
				//���ڴ� �ڱ��ȣ ��� ��δ� �ٲ�
				for (int j = value; j <= n; j += value) {
					switchs[j] = switchs[j] == 0 ? 1 : 0;
				}
			}
			if(gender==2) {
				//���ڴ� �ڱ� ��ȣ ���� �¿��Ī ���� ���� ���� ��� �ٲ۴�.
				int left = value-1;
				int right = value+1;
				switchs[value] = switchs[value] == 0 ? 1 : 0;
				//left �� right ���� �Ǽ�
				while(left > 0 && right <= n && switchs[left] == switchs[right]) {
					switchs[left] = switchs[left] == 0 ? 1 : 0;
					switchs[right] = switchs[right] == 0 ? 1 : 0;
					left--;
					right++;
				}
				
			}
		}
		for (int i = 1; i <= n; i++) {
			System.out.print(switchs[i] + " ");
			if(i%20 == 0) {
				System.out.println();
			}
		}
		
		

	}

}
