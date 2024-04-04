import java.io.*;
import java.util.*;

public class Solution {
	static boolean[] selected;  // selected[x] == true: 단면 x에 약품을 투입
	static boolean[] A; // A[x] == true: 단면 x에 투입한 약품이 A
	static int[][] cells;  // cells[r][c]: r행 c열의 셀이 A면 0, B면 1  
	
	static int T, D, W, K;
	static boolean isFound;
	static int R;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; ++t) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			cells = new int[D][W];
			
			for (int i = 0; i < D; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; ++j) {
					cells[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			isFound = false;
			for (int i = 0; i <= K; ++i) {
				selected = new boolean[D];
				A = new boolean[D];
				
				R = i;
				combination(0, 0);
				
				if (isFound) break;
			}
			
			System.out.printf("#%d %d\n", t, R);
		}
	}
	
	// 0 ~ D까지 수 중 R개 조합 선택
	public static void combination(int idx, int start) {
		if (isFound) return;
		
		if (idx == R) {
			subset(0, 0);
			return;
		}
		
		for (int i = start; i < D; ++i) {
			selected[i] = true;
			combination(idx + 1, i + 1);
			selected[i] = false;
		}
	}
	
	// 선택된  R개 조합의 층 각각에 A, B중 조합을 어떻게 할지 선택
	public static void subset(int idx, int target) {
		if (isFound) return;
		
		if (idx == R) {
			if (check())
				isFound = true;
			return;
		}
		
		// 선택된 
		while (!selected[target]) target++;
		
		subset(idx + 1, target + 1);
		A[target] = true;
		subset(idx + 1, target + 1);
		A[target] = false;
	}
	
	// 현재 조합으로 성능검사를 통과할 수 있는지 확인
	public static boolean check() {
		int col = 0;
		
		if (K == 1) return true;
		
		outer:
		while (col < W) {
			int seq = 1;
			int prev = pick(0, col);
			
			for (int i = 1; i < D; ++i) {
				int cur = pick(i, col);
				
				if (prev + cur == 1) {  // 이전것과 다르다면
					prev = cur;
					seq = 1;
				} else if (++seq >= K) {
					col++;
					continue outer;
				}
			}	
			return false;
		}
		return true;
	}
	
	public static int pick(int r, int c) {
		return selected[r] ? (A[r] ? 1 : 0) : cells[r][c];
	}
}