import java.io.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] wine = new int[n];
        int[] dp = new int[n];
        for (int i = 0; i < n; ++i) {
            wine[i] = Integer.parseInt(br.readLine());
        }
        
        if (n == 1)
            System.out.println(wine[0]);
        else if (n == 2)
            System.out.println(wine[0] + wine[1]);
        else {
            dp[0] = wine[0];
            dp[1] = wine[0] + wine[1];
            dp[2] = Math.max(wine[0] + wine[2], Math.max(wine[1] + wine[2], wine[0] + wine[1]));
            
            for (int i = 3; i < n; ++i) {
                dp[i] = Math.max(dp[i - 3] + wine[i - 1] + wine[i], Math.max(dp[i - 2] + wine[i], dp[i - 1]));
            }
            
            System.out.println(dp[n - 1]);
        }
    }
}