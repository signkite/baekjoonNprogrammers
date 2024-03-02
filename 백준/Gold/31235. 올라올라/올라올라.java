import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        // (앞에 나온 수보다 작은 수가 연속해서 나오는 횟수 + 1) == 정답
        int k = 1;
        int curSeq = 1;
        int curMax = 0;
        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(st.nextToken());

            if (curMax > A[i])
                k = Math.max(k, ++curSeq);
            else {
                curSeq = 1;
                curMax = A[i];
            }
        }

        System.out.println(k);
    }
}