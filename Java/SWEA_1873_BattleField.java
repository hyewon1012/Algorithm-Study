package com.ssafy.off;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SWEA_1873_BattleField {
	//전차는 단 하나
	//포탄 발사하면 벽에 충돌하거나 게임 맵 밖으로 나갈때까지 직진한다. -> 게임 맵 밖으로 포탄이 나가면 아무일도없음.
	//포탄이 벽에 부딪히면 포탄은 사라지고 강철 벽은 그대로 벽돌 벽은 평지로 바뀐다.
	//변화된 맵 상태 출력
	static char[][] map;
	static char[] input;
	static char[] tank = {'^','v','<','>'};
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	static HashMap<Character, Integer> order = new HashMap<>();
	
	public static void init() {
		order.put('U', 0);
		order.put('D', 1);
		order.put('L', 2);
		order.put('R', 3);
	}
	//명령어대로 이동
	//map상태 변화
	public static void solve(int y, int x, int dir, char[][] map, char[] input) {//dir : 탱크 초기 방향
		int py,px; //초기 탱크 위치
		int next_dir = dir;
		py = y;
		px = x;
		for (int i = 0; i < input.length; i++) {
			if(input[i] == 'S') {
				//포탄 발사 : 포탄은 사라지고 강철벽(#)은 그대로 벽돌벽(*)은 평지로바뀜
				//벽에부딪힐때 맵밖으로나갈때
				int p_y = py; //포탄 y 위치
				int p_x = px; //포탄 x 위치
				while(true) {
					int ny = p_y + dy[next_dir];//방향이바뀐걸적용시켜야함
					int nx = p_x + dx[next_dir];
					if(ny >= 0 && ny < map.length && nx >= 0 && nx < map[0].length) {
						//강철벽에 부딪힐때
						if(map[ny][nx] == '#') {
							break;
						}
						//벽돌벽에 부딪힐때
						else if(map[ny][nx] == '*') {
							map[ny][nx] = '.';//평지로바뀐다.
							break;
						}
						//계속 이동
						else {
							p_y = ny;
							p_x = nx;
						}
					}else {
						//맵밖으로나갈때 맵 변화 없음
						break;
					}
				}
			//방향 명령어일때
			}else {
				//한칸씩만 이동
				int ny = py + dy[order.get(input[i])];
				int nx = px + dx[order.get(input[i])];
				next_dir = order.get(input[i]);
				map[py][px] = tank[next_dir]; //다음좌표에 탱크 방향 변경 적용
				if(ny >= 0 && ny < map.length && nx >= 0 && nx < map[0].length) {
					if(map[ny][nx] == '.') {
						map[py][px] = '.'; //원래위치평지로바꿈
						map[ny][nx] = tank[next_dir];
						py = ny;
						px = nx;
					}
				}
				
			}

			
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(in.readLine());
		
		for (int t = 1; t <= tc; t++) {
			//입력
			StringTokenizer st = new StringTokenizer(in.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			
			int startY=0,startX=0;
			int dir = -1;
			
			map = new char[row][col];
			for (int i = 0; i < row; i++) {
				String line = in.readLine();
				for (int j = 0; j < col; j++) {
					map[i][j] = line.charAt(j);
					if(map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>') {
						startY = i;
						startX = j;
						for (int k = 0; k < 4; k++) {
							if(tank[k] == map[i][j])
								dir = k;
						}
					}
				}
			}
			int size = Integer.parseInt(in.readLine());

			input = in.readLine().toCharArray();
			
			init();
			
			solve(startY, startX, dir, map, input);
			System.out.print("#"+t+ " ");
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			

		}
		
	}

}
