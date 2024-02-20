import java.io.*;
import java.util.*;

public class Main {
	static int row, col;
	static int[][] board;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		board = new int[row][col];
		
		for (int i = 0; i < row; ++i) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < col; ++j) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		int prevMelted = 0, melted;
		while ((melted = melt()) != 0) {
			prevMelted = melted;
			time++;
		}
		
		System.out.println(time);
		System.out.println(prevMelted);
	}
	
	// board의 치즈를 녹이고 녹은 양을 반환하는 메서드
	public static int melt() {
		int meltedAmount = 0;
		
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0, 0});
		boolean[][] check = new boolean[row][col];
		check[0][0] = true;
		
		while (!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.poll()[1];
			
			for (int i = 0; i < 4; ++i) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || row <= nx) continue;
				if (ny < 0 || col <= ny) continue;
				if (check[nx][ny]) continue;
				
				check[nx][ny] = true;
				if (board[nx][ny] == 1) {
					board[nx][ny] = 0;
					meltedAmount++;
				} else
					q.offer(new int[] {nx, ny});
			}
		}
		
		return meltedAmount;
	}
}