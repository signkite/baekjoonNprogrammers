import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dp = new int[K + 1];
		
		for (int i = 0; i < N; ++i) { 
			st = new StringTokenizer(br.readLine());
			
			int curW = Integer.parseInt(st.nextToken());
			int curV = Integer.parseInt(st.nextToken());
			
			for (int weight = K; weight >= curW; --weight) {
				dp[weight] = Math.max(dp[weight - curW] + curV, dp[weight]);
			}
		}
		
		System.out.println(dp[K]);
	}
}