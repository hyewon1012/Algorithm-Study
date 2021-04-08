package com.java.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
//msg print
public class BOJ_말이되고픈원숭이 {

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[] hx = { -2, -2, 2, 2, 1, -1, 1, -1 };//말처럼 움직일 때의 x좌표
	static int[] hy = { 1, -1, 1, -1, 2, 2, -2, -2 };																												// 시계방향으로
																														// 8개
	static int K;
	static int w, h;
	static int[][] map;
	static boolean[][][] visit;//원숭이처럼 or 말처럼 뛴(횟수별) 방문처리 각각
	
	static int[][][] visit2;
	static int count = -1;
	static int num = 0;

	static public class Monkey {
		int x;
		int y;
		int cnt;
		int k;
		int num;

		public Monkey( int x, int y, int cnt, int k, int num) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.k = k;//말처럼 움직인 횟수정보
			this.num = num;//큐에 들어가는 순서
		}

		@Override
		public String toString() {
			return "Monkey [k=" + k + ", num=" + num + "]";
		}

		
	}
	static String src = "1\r\n" + 
			"4 4\r\n" + 
			"0 0 0 0\r\n" + 
			"1 0 0 0\r\n" + 
			"0 0 1 0\r\n" + 
			"0 1 0 0";
	
	

	public static void main(String[] args) {
		String src2="1\r\n" + 
				"3 3\r\n" + 
				"0 0 0\r\n" + 
				"1 0 0\r\n" + 
				"0 0 0";

		//Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(src2);
		Queue<Monkey> queue = new LinkedList<>();

		K = sc.nextInt(); // 말 처럼 움직일 수 있는 회수 . 0~30
		w = sc.nextInt();
		h = sc.nextInt();

		map = new int[h][w];
		
		//k=1이면 [][][0]에는 원숭이처럼 움직인 경우의 방문 체크값(K사용 안함), [][][1]말처럼  움직인 경우의 방문 체크값
		visit = new boolean[K + 1][h][w];//h*w 배열이 k개
		visit2 = new int[K + 1][h][w];//h*w 배열이 k개

		// 입력받기
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		visit[0][0][0] = true; // 방문표시
		visit2[0][0][0] = ++count; // 방문 순서 표시
		queue.add(new Monkey(0, 0, 0, 0, num++)); // k가 0일때 부터 시작(말처럼 움직인 횟수를 0으로 하고 시작->원숭이처럼 움직이는 경우부터 보겠다)

		while (!queue.isEmpty()) {
			Monkey tommy = queue.poll();
			System.out.println(tommy.num + "번이  큐에서 나옴");
			
			//1) 종료 조건. 도착점에 도달.
			if (tommy.x == h - 1 && tommy.y == w - 1) { // 끝에 도달했을 때,
				//System.out.println(tommy.num);
				for (int i = 0; i <= K; i++) {
					System.out.println(i + "번 판");
					for (int j = 0; j < h; j++) {
						for (int k = 0; k < w; k++) {
							System.out.print(visit2[i][j][k] + "\t");
						}
						System.out.println();
					}
					System.out.println("-------------------");
				}			
				
				System.out.println(tommy.cnt);
				return;

			}

			//2) 아직 도착 안했어. 그럼 뛰어서 이동해야 돼
			//2-1. 원숭이처럼만 뛰어보기.  4방 서치 ->visit[nx][ny][0]에 방문표시
			for (int d = 0; d < 4; d++) { 
				int nx = tommy.x + dx[d];
				int ny = tommy.y + dy[d];
				int cnt = tommy.cnt;//움직인 횟수
				int horsemove = tommy.k;//말처럼 움직인 횟수
				
				if (nx < 0 || ny < 0 || nx >= h || ny >= w)// boundary check
					continue; 
				
				if (map[nx][ny] == 1)// 벽인경우
					continue;

				if (visit[horsemove][nx][ny])// 방문했으면
					continue; 

				// 들린적이 없거나, 벽이 아닌경우에는
				visit[horsemove][nx][ny] = true; // 방문처리
				visit2[horsemove][nx][ny] = ++count; // 방문처리
				Monkey qq = new Monkey(nx, ny, cnt + 1, horsemove, num++);
				queue.add(qq); //원숭이가 새위치로 움직임
				System.out.println("\t"+qq+ "번 큐에 들어감" );
			}
			
			//2-2. 4방 서치  다 한다음에 말처럼 뛰어 볼건데  ->visit[k][nx][ny]에 방문표시
			// m.k 가 주어진 K와 같다면 더이상 말처럼 뛰지 못한다.
			if (tommy.k == K)
				continue;

			for (int d = 0; d < 8; d++) {
				int nx = tommy.x + hx[d];
				int ny = tommy.y + hy[d];				
				int cnt = tommy.cnt;				
				int horsemove = tommy.k + 1;//말처럼 움직인 횟수 증가.  말처럼 뛰어 볼거니까
				
				if (nx < 0 || ny < 0 || nx >= h || ny >= w)// boundary check
					continue; 
				
				
				if (map[nx][ny] == 1)// 벽인경우
					continue;

				if (visit[horsemove][nx][ny])// 방문했으면 패스
					continue; 
				
				// 들린적이 없거나, 벽이 아닌경우에는
				// m.k는 while 실행 시 , 이전에 취한 원숭이 움직임의 종류
				visit[horsemove][nx][ny] = true; // 방문처리
				visit2[horsemove][nx][ny] = ++count;
				
				Monkey qq = new Monkey(nx, ny, cnt + 1, horsemove, num++);
				queue.add(qq); // k 하나 올리고
				System.out.println("\t"+qq+ "번 큐에 들어감" );
			}
		}

		System.out.println("-1");
	}	
}
