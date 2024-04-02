import java.util.*;

class Main {
	public static void main(String[] args) {
		long num = (new Scanner(System.in)).nextLong();
		
		int limit = (int) (num % 1_500_000);
		
		int[] dp = new int[limit + 1];
		dp[0] = 0;
		dp[1] = 1;
		
		for (int i = 2; i <= limit; ++i) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % 1_000_000;
		}
		
		System.out.println(dp[limit]);
	}
}