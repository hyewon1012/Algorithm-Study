package com.ssafy.off;

import java.util.Scanner;

public class Baekjoon_17478 {
	StringBuilder sb = new StringBuilder();
	static String question = "\"재귀함수가 뭔가요?\"\n";
	static String teaching1 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n";
	static String teaching2 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n";
	static String teaching3 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n";
	static String answer = "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n";
	static String ending = "라고 답변하였지.\n";
	
	static void makeSpace(int depth) {
        StringBuilder prefix = new StringBuilder("");
        for (int i=1; i <= depth*4; i++){
            prefix.append("_");
        }
        System.out.print(prefix);
	}
	
	static void recursive(int n, int end, int depth) {
		makeSpace(depth);
		System.out.print(question);
		//1. base case
		if (n == end) {
			makeSpace(depth);
			System.out.print(answer);
		}
		else {
			makeSpace(depth);
			System.out.print(teaching1);
			makeSpace(depth);
			System.out.print(teaching2);
			makeSpace(depth);
			System.out.print(teaching3);
			recursive(n+1, end, depth+1);
		}
		makeSpace(depth);
		System.out.print(ending);

	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		recursive(0, n, 0);
	}

}
