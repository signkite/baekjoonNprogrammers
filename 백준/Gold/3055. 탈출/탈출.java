import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static int[][] map;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		// 지도 입력 및 고슴도치의 시작 위치 저장
		Queue<int[]> q = new ArrayDeque<>();
		
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
				
				if (map[i][j] == 'S')
					q.offer(new int[] {i, j});
			}
		}
		
		// bfs로 최소 시간 구하기
		int time = 0;
		boolean isSafe = false;
		boolean[][] check = new boolean[R][C];
		
		outer:
		while(!q.isEmpty()) {
			flood();
			time++;
			int size = q.size();
			
			for (int i = 0; i < size; ++i) {
				int x = q.peek()[0];
				int y = q.poll()[1];
				
				for (int d = 0; d < 4; ++d) {
					int nx = x + dx[d]; 
					int ny = y + dy[d];
					
					if (!validCoord(nx, ny)) continue;
					if (check[nx][ny]) continue;
					if (map[nx][ny] == '*' || map[nx][ny] == 'X') continue;
					
					// 목적지 도착시
					if (map[nx][ny] == 'D') {
						isSafe = true;
						break outer;
					}
					
					check[nx][ny] = true;
					q.offer(new int[] {nx, ny});
				}
			}	
		}
		
		System.out.println(isSafe ? time : "KAKTUS");
	}
	
	public static void flood() {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[R][C];
		
		for (int i = 0; i < R; ++i) {
			for (int j = 0; j < C; ++j) {
				if (visit[i][j] || map[i][j] != '*') continue;
				
				q.offer(new int[] {i, j});
				
				while (!q.isEmpty()) {
					int x = q.peek()[0];
					int y = q.poll()[1];
					
					for (int d = 0; d < 4; ++d) {
						int nx = x + dx[d];
						int ny = y + dy[d];
						
						if (!validCoord(nx, ny)) continue;
						if (visit[nx][ny]) continue;
						if (map[nx][ny] == 'D' || map[nx][ny] == 'X') continue;
						
						// 연결된 물이라면
						if (map[nx][ny] == '*') {
							visit[nx][ny] = true;
							q.offer(new int[] {nx, ny});
						}
						
						// 물과 인접한 땅이라면
						else {
							visit[nx][ny] = true;
							map[nx][ny] = '*';
						}
					}
				}
			}
		}	
	}
	
	public static boolean validCoord(int x, int y) {
		return 0 <= x && x < R && 0 <= y && y < C;
	}
}