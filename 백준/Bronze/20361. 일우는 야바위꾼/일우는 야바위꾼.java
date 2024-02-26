import java.io.*;
import java.util.*;

public class Main {
	static int N, X, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // curs[x] == 1 : 왼쪽에서 x번째 컵에 사탕이 들어있음.
        int[] cups = new int[N + 1];
        cups[X] = 1;

        for (int k = 0; k < K; ++k) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            int temp = cups[A];
            cups[A] = cups[B];
            cups[B] = temp;
        }

        int answer = 0;
        for (int i = 1; i <= N; ++i) {
            if (cups[i] == 1) {
                answer = i;
                break;
            }
        }

        System.out.println(answer);
	}
}