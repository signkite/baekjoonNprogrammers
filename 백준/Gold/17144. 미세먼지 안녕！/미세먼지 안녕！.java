import java.io.*;
import java.util.*;

class Main {
	static int R, C, T;
	static int[][] A;
	static int[] upside;
	static int[] downside;
	
	// 0 ~ 3: 우, 상, 좌, 하
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		A = new int[R][C];
		for (int i = 0; i < R; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; ++j) {
				A[i][j] = Integer.parseInt(st.nextToken());
				
				if (A[i][j] == -1) {
					if (upside == null) upside = new int[] {i, j};
					else downside = new int[] {i, j};
				}
			}
		}
		
		while (T-- > 0) {
			spread();
			circulate(true);
			circulate(false);
			
		}
		
		int left = 0;
		for (int[] line: A) {
			for (int num: line) {
				left += num;
			}
		}
		System.out.println(left + 2);  // 공기청정기 있는 부분 -1 감안하여 +2
	}
	
	// 미세먼지 확산
	public static void spread() {
		int[][] nextA = new int[R][C];
		
		for (int r = 0; r < R; ++r) {
			for (int c = 0; c < C; ++c) {
				if (A[r][c] == 0) continue;
				
				if (A[r][c] == -1) {
					nextA[r][c] = -1;
					continue;
				}
				
				int moved = 0;  // 미세먼지가 옮겨간 칸의 수
				for (int d = 0; d < 4; ++d) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if (!validCoord(nr, nc)) continue;
					if (A[nr][nc] < 0) continue;
					
					nextA[nr][nc] += A[r][c] / 5;
					moved++;
				}
				
				nextA[r][c] += A[r][c] - (A[r][c] / 5) * moved;
			}
		}
		
		A = nextA;
	}
	
	// 공기 순환
	public static void circulate(boolean isUpside) {
		int d = 0;
		int r = isUpside ? upside[0] : downside[0];
		int c = isUpside ? upside[1] : downside[1]; 
		
		int prev = 0;
		while (true) {
			if (!validCoord(r + dr[d], c + dc[d]))
				d = isUpside ? (d + 1) % 4 : (d + 3) % 4;
				
			r += dr[d];
			c += dc[d];
			
			if (A[r][c] == -1) break;
			
			int temp = prev;
			prev = A[r][c];
			A[r][c] = temp; 
		}
	}
	
	public static boolean validCoord(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
}