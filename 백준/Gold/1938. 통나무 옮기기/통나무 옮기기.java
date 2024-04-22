import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] map;
	static int[][] coord = new int[4][2];
	
	static int ex, ey, ed;
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		boolean startB = true, startE = true;
		
		for (int i = 0; i < N; ++i) {
			String line = br.readLine();
			for (int j = 0; j < N; ++j) {
				char c = line.charAt(j);
				
				if (c == 'B') {
					if (startB)  {
						coord[0] = new int[] {i, j};
						startB = false;
					}
					else coord[1] = new int[] {i, j};
					map[i][j] = 0;
				} else if (c == 'E'){
					if (startE) {
						coord[2] = new int[] {i, j};
						startE = false;
					}
					else coord[3] = new int[] {i, j};
					map[i][j] = 0;
				} else {
					map[i][j] = c - '0';
				}
			}
		}
		
		boolean[][][] check = new boolean[N][N][2];
		Queue<int[]> q = new ArrayDeque<>();
		
		if (coord[0][0] == coord[1][0]) {
			// B가 가로로 누워있는 경우
			q.offer(new int[] {coord[0][0], (coord[0][1] + coord[1][1]) / 2, 0, 0});
		} else {
			q.offer(new int[] {(coord[0][0] + coord[1][0]) / 2, coord[0][1], 1, 0});
		}
		
		if (coord[2][0] == coord[3][0]) {
			// E가 가로로 누워있는 경우
			ex = coord[2][0];
			ey = (coord[2][1] + coord[3][1]) / 2;
			ed = 0;
		} else {
			ex = (coord[2][0] + coord[3][0]) / 2;;
			ey = coord[2][1];
			ed = 1;
		}
		
		int answer = 0;
		
		outer:
		while (!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.peek()[1];
			int d = q.peek()[2];
			int move = q.poll()[3];
			
			// 회전하는 경우
			int nd = Math.abs(d - 1);
			if (!check[x][y][nd] && canRotate(x, y)) {
				if (isFinish(x, y, nd)) {
					answer = move + 1;
					break outer;
				}
				
				check[x][y][nd] = true;
				q.offer(new int[] {x, y, nd, move + 1});
			}
			
			// 이동하는 경우
			for (int i = 0; i < 4; ++i) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (!possiblePosition(nx, ny, d)) continue;
				if (check[nx][ny][d]) continue;
				
				if (isFinish(nx, ny, d)) {
					answer = move + 1;
					break outer;
				}
				
				check[nx][ny][d] = true;
				q.offer(new int[] {nx, ny, d, move + 1});
			}
 		}
		
		System.out.println(answer);
	}
	
	// x, y는 중심좌표
	public static boolean possiblePosition(int x, int y, int d) {
		// 가로로 누운 경우
		if (d == 0) {
			if (0 <= x && x < N && 1 <= y && y < N - 1)
				return map[x][y - 1] + map[x][y] + map[x][y + 1] == 0;
			return false;
		}
		
		// 세로로 서있는 경우
		else {
			if (1 <= x && x < N - 1 && 0 <= y && y < N)
				return map[x - 1][y] + map[x][y] + map[x + 1][y] == 0;
			return false;
		}
	}
	
	public static boolean canRotate(int x, int y) {
		for (int i = x - 1; i <= x + 1; ++i) {
			for (int j = y - 1; j <= y + 1; ++j) {
				if (i < 0 || N <= i || j < 0 || N <= j) return false;
				if (map[i][j] == 1) return false;
			}
		}
		return true;
	}
	
	public static boolean isFinish(int x, int y, int d) {
		return x == ex && y == ey && d == ed;
	}
}