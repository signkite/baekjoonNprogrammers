import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] wall;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		wall = new int[N][N];
		
		for (int i = 0; i < N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				wall[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 1, 0);
		System.out.println(answer);
	}
	
	// x, y는 파이프의 끝 좌표
	// d는 방향 : (0 → / 1 ↘ / 2 ↓)
	public static void dfs(int x, int y, int d) {
		if (x == N - 1 && y == N - 1) {
			answer++;
			return;
		}
		
		// 가로로 돌려서 이동
		if (d != 2) {
			if (y + 1 < N && wall[x][y + 1] == 0)
				dfs(x, y + 1, 0);
		}
		
		// 대각선으로 돌려서 이동
		if (x + 1 < N && y + 1 < N) {
			if (wall[x + 1][y] == 0 && wall[x][y + 1] == 0 && wall[x + 1][y + 1] == 0)
				dfs(x + 1, y + 1, 1);			
		}
		
		// 세로로 돌려서 이동
		if (d != 0) {
			if (x + 1 < N && wall[x + 1][y] == 0)
				dfs(x + 1, y, 2);			
		}
	}
}