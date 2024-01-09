import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int N, answer = 0;
        int[] A = new int[2000];

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬 후, index 0 ~ (N - 1) 까지 해당 숫자에 대해 좋은 수인지 판단
        A = Arrays.copyOfRange(A, 0, N);
        Arrays.sort(A);

        // 좋은 수인지 여부 투포인터로 판단
        int s, e, sum;
        for (int i = 0; i < N; ++i) {
            s = 0; e = N - 1;
            while (s < e) {
                if (s == i) {
                    s++;
                    continue;
                } else if (e == i) {
                    e--;
                    continue;
                }
                
                sum = A[s] + A[e];
                if (sum < A[i]) s++;
                else if (sum > A[i]) e--;
                else {
                    answer++;
                    break;
                }
            }
        }

        System.out.println(answer);

    }
}