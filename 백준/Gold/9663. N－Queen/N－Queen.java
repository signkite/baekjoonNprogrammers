import java.util.*;

public class Main {
    static int N;
    static int answer;
    static int[] queens;

    // i 번째 열의 queen을 어디에 놓을지 탐색
    public static void nQueen(int i) {
        if (i == N) {
            answer++;
            return;
        }

        // i 번째 열의 queen을 j 번째 행에 놓으려 한다.
        outer:
        for (int j = 0; j < N; ++j) {
            // 앞선 queen 들로 인해 j 번째 행에 놓을 수 없는지 검사
            for (int k = 0; k < i; ++k) {
                if (j == queens[k] - (i - k) || j == queens[k] + (i - k) || j == queens[k]) {
                    continue outer;
                }
            }
            queens[i] = j;
            nQueen(i + 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        queens = new int[N];
        nQueen(0);
        System.out.println(answer);

        // boolean[N] 을 만들어놓고, 해당 수를 뺀다.
        // 그리고 돈다.
    }
}