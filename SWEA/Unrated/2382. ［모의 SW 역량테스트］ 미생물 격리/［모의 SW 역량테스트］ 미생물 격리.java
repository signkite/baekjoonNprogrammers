import java.io.*;
import java.util.*;

public class Solution {
	static int T;
	static int N, M, K;
	static List<Germ> germs = new ArrayList<>();
	static Germ[][][] board;
	
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, -1, 1};
	
	static class Germ {
		int time;
		int num;
		int direction;
		
		public Germ(int time, int num, int direction) {
			this.time = time;
			this.num = num;
			this.direction = direction;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; ++t) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			board = new Germ[M + 1][N][N];
			
			// 미생물 정보 입력받기
			for (int i = 0; i < K; ++i) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				int direction = Integer.parseInt(st.nextToken());
				
				board[0][x][y] = new Germ(0, num, direction);
			}
			
			for (int time = 1; time <= M; ++time) {
				
				for (int x = 0; x < N; ++x) {
					for (int y = 0; y < N; ++y) {
						
						int curDirection = -1;
						int sum = 0;
						int max = 0;
						
						// 이전 시간에 한칸 떨어져있는 미생물군을 조사해 현재 칸에 오게 될 미생물군 판단
						for (int i = 1; i <= 4; ++i) {
							int nx = x + dx[i];
							int ny = y + dy[i];
							
							if (invalidCoord(nx, ny)) continue;
							if (board[time - 1][nx][ny] == null) continue;
							
							Germ curGerm = board[time - 1][nx][ny];
							
							// 현재 칸으로 올 군집이 아니라면 건너뛰기
							if (curGerm.time != time - 1 || curGerm.direction != turn(i))
								continue;
							
							sum += curGerm.num;
							
							if (max < curGerm.num) {
								max = curGerm.num;
								curDirection = curGerm.direction;								
							}
						}
						
						// 현재 칸으로 오는 미생물이 존재한다면 현재 칸에 미생물 상태 업데이트
						if (sum > 0) {
							
							// 현재 칸이 약품 구역이라면
							if (isDangerZone(x, y)) {
								curDirection = turn(curDirection);
								sum /= 2;
							}
							
							board[time][x][y] = new Germ(time, sum, curDirection);
						}
					}
				}	
			}
			
			//debug
//			for (Germ[][] met: board) {
//				System.out.println();
//				for (Germ[] gs: met) {
//					for (Germ g: gs) {
//						if (g == null)
//							System.out.print("[       ]");
//						else
//							System.out.printf("[%2d|%4d]", g.direction, g.num);
//					}
//					System.out.println();
//				}
//				System.out.println();
//			}
			
			
			
			// 남아있는 미생물 수 구하기
			int finalGerms = 0;
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					if (board[M][i][j] == null) continue;
					
					finalGerms += board[M][i][j].num;
				}
			}
			System.out.printf("#%d %d\n", t, finalGerms);
			
		}
	}
	
	public static boolean isDangerZone(int x, int y) {
		return x == N - 1 || y == N - 1 || x == 0 || y == 0;
	}
	
	public static boolean invalidCoord(int x, int y) {
		return x < 0 || N <= x || y < 0 || N <= y;
	}
	
	public static int turn(int d) {
		if (d == 1) return 2;
		if (d == 2) return 1;
		if (d == 3) return 4;
		else return 3;
	}
}