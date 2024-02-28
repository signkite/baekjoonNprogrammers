import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] map;
	static long[][][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		// dp[x][y][d] : x행 y열로 파이프가 d인상태로 도달하는 경우
		// d : 0 - 가로, 1 - 대각선, 2 - 세로
		dp = new long[N][N][3];
		
		// 지도 입력받기
		for (int i = 0; i < N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// dp 확인하지 않은 지점 -1로 표시, 벽 있는 지점 0 표시
		for (int x = 0; x < N; ++x)
			for (int y = 0; y < N; ++y)
				for (int d = 0; d < 3; ++d) {
					dp[x][y][d] = map[x][y] == 1 ? 0L : -1L;
				}
		
		// 파이프 끝이 위치할 수 없는 지점 0으로 초기화
		for (int x = 0; x < N; ++x)
			for (int y = 0; y < 2; ++y)
				for (int d = 0; d < 3; ++d)
					dp[x][y][d] = 0L;
		
		// 처음 파이프 위치
		dp[0][1][0] = 1L;
		
		// 파이프 끝이 (N - 1)행 (N - 1)열에 위치하는 경우 구하기
		long case0 = search(N - 1, N - 1, 0);
		long case1 = search(N - 1, N - 1, 1);
		long case2 = search(N - 1, N - 1, 2);
		
		System.out.println(case0 + case1 + case2);
	}
	
	public static long search(int x, int y, int d) {
		
		if (dp[x][y][d] != - 1) {
			return dp[x][y][d];
		}
		
		long count = 0;
		
		// 파이프가 가로로 현재위치까지 연결되는 경우
		if (d == 0 && y >= 1) {
			count += search(x, y - 1, 0) + search(x, y - 1, 1);
		}
		
		// 파이프가 대각선으로 현재위치까지 연결되는 경우
		else if (d == 1 && x >= 1 && y >= 1 && map[x - 1][y] != 1 && map[x][y - 1] != 1) {
			count += search(x - 1, y - 1, 0) + search(x - 1, y - 1, 1) + search(x - 1, y - 1, 2);
		}
		
		// 파이프가 세로로 현재위치까지 연결되는 경우
		else if (d == 2 && x >= 1) {
			count += search(x - 1, y, 1) + search(x - 1, y, 2);
		}
		
		return dp[x][y][d] = count;
	}
}