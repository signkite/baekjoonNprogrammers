import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static char[][] RBmatrix;
	static char[][] RGBmatrix;
	static boolean[][] RBcheck;
	static boolean[][] RGBcheck;
	static int RBcount;
	static int RGBcount;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		RBmatrix = new char[N][N];
		RGBmatrix = new char[N][N];
		RBcheck = new boolean[N][N];
		RGBcheck = new boolean[N][N];
		
		for (int i = 0; i < N; ++i) {
			String line = br.readLine();
			for (int j = 0; j < N; ++j) {
				if (line.charAt(j) == 'G') {
					RBmatrix[i][j] = 'R';
					RGBmatrix[i][j] = 'G';
				} else {
					RBmatrix[i][j] = RGBmatrix[i][j] = line.charAt(j);
				}
			}
		}
		
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (!RBcheck[i][j]) {
					RBcount++;
					bfs(RBmatrix, RBcheck, i, j);
				}
				
				if (!RGBcheck[i][j]) {
					RGBcount++;
					bfs(RGBmatrix, RGBcheck, i, j);
				}
			}
		}
		
		System.out.printf("%d %d", RGBcount, RBcount);
	}
	
	public static void bfs(char[][] mat, boolean[][] check, int startX, int startY) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {startX, startY});
		check[startX][startY] = true;
		char color = mat[startX][startY];
		
		while (!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.poll()[1];
			
			for (int i = 0; i < 4; ++i) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || N <= nx) continue;
				if (ny < 0 || N <= ny) continue;
				if (check[nx][ny] || mat[nx][ny] != color) continue;
				
				check[nx][ny] = true;
				q.offer(new int[] {nx, ny});
			}
		}
	}
}