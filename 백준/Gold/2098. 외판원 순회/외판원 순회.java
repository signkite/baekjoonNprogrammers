import java.io.*;
import java.util.*;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static int[][] W;
	static int A;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		W = new int[N][N];
		
		for (int i = 0; i < N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < N; ++j) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// tsp[i][A] 0번 도시에서 A에 속한 도시를 거친 후 i번 도시로 오는 최소 비용
		A = (int) Math.pow(2, N - 1);
		dp = new int[N][A];
		
		// 구하지 않은 값은 모두 -1 로 초기화
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < A; ++j) {
				dp[i][j] = -1;
			}
		}
		
		// 초기값 설정
		dp[0][0] = 0;
		for (int i = 1; i < N; ++i) {
			if (W[i][0] == 0) dp[i][0] = Integer.MAX_VALUE;
			else dp[i][0] = W[i][0];
		}
		
		System.out.println(tsp(0, A - 1));
	}
	
	public static int tsp(int i, int a) {
		if (dp[i][a] != -1) {
//			System.out.println(i + "/" + a + "  " + dp[i][a]);
			return dp[i][a];
		}
		
		int minCost = Integer.MAX_VALUE;
		for (int j = 1; j < N; ++j) {
			if ((a & 1 << (j - 1)) == 0) continue;
			
			int prev = tsp(j, a & ~(1 << (j - 1)));
			if (prev == Integer.MAX_VALUE) continue;
			if (W[i][j] == 0) continue;
			
			minCost = Math.min(minCost, W[i][j] + prev);
		}
//		System.out.println(i + "/" + a + "  " + dp[i][a]);
		return dp[i][a] = minCost;
	}
}