import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());

        long[] sum = new long[1000001];  // sum[i] : A1 ~ Ai 까지 숫자의 합
        int[] mod = new int[1000];  // mod[i] : sum[i] % M
        long answer = 0;

        sum[0] = 0;
        for (int i = 1; i <= N; i++) {
            sum[i] = Integer.parseInt(st.nextToken()) + sum[i - 1];
            int idx = (int)(sum[i] % M);
            if (idx == 0) answer++;
            mod[idx]++;
        }


        for (int i = 0; i < M; i++) {
            if (mod[i] < 2) continue;
            int n = mod[i];
            answer += (long)n * (n - 1) / 2;
        }

        System.out.println(answer);
    }
}