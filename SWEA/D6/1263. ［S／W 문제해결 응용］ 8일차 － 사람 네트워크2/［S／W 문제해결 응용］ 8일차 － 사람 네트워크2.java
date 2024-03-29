import java.io.*;
import java.util.*;

public class Solution {
	static int T;
	static int N;
	static int[][] mat;
	
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; ++t) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			mat = new int[N][N];
			
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					if (st.nextToken().equals("1"))
						mat[i][j] = 1;
					else
						mat[i][j] = INF;
				}
			}
			
			// 플로이드 - 워셜 알고리즘
			for (int k = 0; k < N; ++k) {
				for (int i = 0; i < N; ++i) {
					for (int j = 0; j < N; ++j) {
						if (mat[i][k] == INF || mat[k][j] == INF) continue;
						
						mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
					}
				}
			}
			
			int minCC = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; ++i) {
				int sum = 0;
				
				for (int j = 0; j < N; ++j) {
					if (j == i) continue;
					
					sum += mat[i][j];
				}
				
				minCC = Math.min(minCC, sum);
			}
			
			System.out.printf("#%d %d\n", t, minCC);
		}
	}
}