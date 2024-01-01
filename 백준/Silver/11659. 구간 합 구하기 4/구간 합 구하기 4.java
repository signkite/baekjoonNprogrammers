import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] line = bf.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);

        // dp[x] : 1번째 수부터 x번째 수까지의 합
        int[] dp = new int[N + 1];
        int i = 0;
        for(String n: bf.readLine().split(" ")) {
            int cur_num = Integer.parseInt(n);
            dp[i + 1] = dp[i] + cur_num;
            i++;
        }

        int start, end;
        for (i = 0; i < M; ++i) {
            line = bf.readLine().split(" ");
            start = Integer.parseInt(line[0]);
            end =Integer.parseInt(line[1]);
            System.out.println(dp[end] - dp[start - 1]);
        }
    }
}