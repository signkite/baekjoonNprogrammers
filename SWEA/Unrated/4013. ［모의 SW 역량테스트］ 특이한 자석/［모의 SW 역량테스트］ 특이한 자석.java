import java.io.*;
import java.util.*;

class Solution {
	static int T;
	static int[][] gears;
	static int[] topPosition;  // 각 기어의 12시 방향의 인덱스를 저장
	static int[] dt;  // topPosition값을 임시 저장 
	static int K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; ++t) {
			
			K = Integer.parseInt(br.readLine());
			
			gears = new int[4][8];
			topPosition = new int[4];  // 각 기어의 12시 방향의 인덱스를 저장
			dt = new int[4];  // topPosition값을 임시 저장 

			for (int i = 0; i < 4; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; ++j) {
					gears[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < K; ++i) {
				st = new StringTokenizer(br.readLine());
				
				int gearNum = Integer.parseInt(st.nextToken()) - 1;			
				int prevL = Integer.parseInt(st.nextToken());			
				int prevR = prevL;
				
				dt[gearNum] = (topPosition[gearNum] - prevL + 8) % 8;
				for (int j = 0; j < 3; ++j) {
					prevL = rotate(gearNum + j, prevL, gearNum + j + 1);
					prevR = rotate(gearNum - j, prevR, gearNum - j - 1);
				}
				
				for (int k = 0; k < 4; ++k) {
					topPosition[k] = dt[k];
				}
			}
			
			int score = 0;
			score += gears[0][topPosition[0]];
			score += gears[1][topPosition[1]] * 2;
			score += gears[2][topPosition[2]] * 4;
			score += gears[3][topPosition[3]] * 8;
			
			System.out.printf("#%d %d\n", t, score);
		}
	}
	
	// cur이 move 방향으로 움직였을 때 next가 움직인 방향을 반환
	public static int rotate(int cur, int move, int next) {
		if (next < 0 || 4 <= next || move == 0) return 0;
		
		int curAdj, nextAdj;  // cur과 next가 각각 맞닿은 부분의 인덱스
		
		// cur의 오른쪽에 next가 있는 상황
		if (next > cur) {
			curAdj = (topPosition[cur] + 2) % 8;
			nextAdj = (topPosition[next] + 6) % 8;
		}
		
		// cur의 왼쪽에 next가 있는 상황
		else {
			curAdj = (topPosition[cur] + 6) % 8;
			nextAdj = (topPosition[next] + 2) % 8;
		}
		
		// cur과 next가 인접한 부분의 극성이 다르다면 next를 cur 반대방향으로 회전		
		if (gears[cur][curAdj] != gears[next][nextAdj])
			dt[next] = (topPosition[next] + move + 8) % 8;
		else
			move = 0;
		
		return -move;
	}
}