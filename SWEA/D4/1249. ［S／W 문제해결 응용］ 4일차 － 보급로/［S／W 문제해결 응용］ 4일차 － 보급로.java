import java.io.*;
import java.util.*;

public class Solution {
	static int T, N;
	static int[][] map;
	static int[][] minTime;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; ++t) {
			N = Integer.parseInt(br.readLine());
			
			// 지도 입력
			map = new int[N][N];
			minTime = new int[N][N];
			for (int i = 0; i < N; ++i) {
				String line = br.readLine();
				for (int j = 0; j < N; ++j) {
					map[i][j] = line.charAt(j) - '0';
					minTime[i][j] = INF;
				}
			}
			
			repave();
			System.out.printf("#%d %d\n", t, minTime[N - 1][N - 1]);
		}
	}
	
	public static void repave() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0, 0});
		minTime[0][0] = 0;
		
		while (!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.poll()[1];
			
			for (int i = 0; i < 4; ++i) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (!validCoord(nx, ny)) continue;
				if (minTime[nx][ny] <= minTime[x][y] + map[nx][ny]) continue;
				
				minTime[nx][ny] = minTime[x][y] + map[nx][ny];
				q.offer(new int[] {nx, ny});
			}
		}
	}
	
	public static boolean validCoord(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}
}