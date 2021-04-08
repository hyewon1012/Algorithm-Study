package com.ssafy.off;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Baekjoon_12927_배수스위치 {
/**
 * 모든 전구를 끌때 스위치 몇번 눌러야하는지
 * i번스위치 i번 배수 스위치 모두 끔
 * 101010101 -> 010101010 -> 
 * */
	static ArrayList<Character> switchs;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		switchs = new ArrayList<Character>();
		
		char[] temp = br.readLine().trim().toCharArray();
		switchs.add('Y'); //dummy data
		for (int i = 0; i < temp.length; i++) {
			switchs.add(temp[i]);
		}
		int ans = 0;
		for (int i = 2; i < switchs.size(); i++) {
			if(switchs.get(i) != switchs.get(i-1)) {
				int index = i;
				int cnt = 1;
				while(index < switchs.size()) {
					if(switchs.get(index) == 'Y') {
						switchs.set(index, 'N');
					}else {
						switchs.set(index, 'Y');
					}
					cnt++;
					index = i*cnt;
				}
				ans++;
			}
		}
		
		if(switchs.get(1) == 'Y') {
			System.out.println(ans+1);
		}else {
			System.out.println(ans);
		}

	}
	

}
