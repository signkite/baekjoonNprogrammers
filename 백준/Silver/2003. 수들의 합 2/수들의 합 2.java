import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        long M = Long.parseLong(line[1]);

        // A[1] ~ A[i] 까지의 합을 sum[i]에 저장
        // sum[x ] - sum[y - 1] 는 A[y] ~ A[x] 의 합을 의미하게 된다.
        long[] sum = new long[N + 1];
        line = br.readLine().split(" ");

        for (int i = 1; i <= N; ++i) {
            sum[i] = sum[i - 1] + Long.parseLong(line[i - 1]);
        }

        int cnt = 0;
        for (int i = 0; i <= N - 1; ++i) {
            for (int j = i + 1; j <= N; ++j) {
                if (sum[j] - sum[i] == M) cnt++;
            }
        }

        System.out.println(cnt);
    }
}