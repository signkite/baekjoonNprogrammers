import java.io.*;
import java.util.*;

public class Main {
    static int N, d, k, c;
    static int[] sushi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        sushi = new int[N];

        for (int i = 0; i < N; ++i)
            sushi[i] = Integer.parseInt(br.readLine());

        solve();
    }

    public static void solve() {
        // count[x] : 범위 내에 초밥 x 가 몇 번 등장하는지 체크
        int[] count = new int[d + 1];
        int max = 0;  // 초밥 종류 최대값
        int sushiTypes = 0;  // 범위 내 먹을 수 있는 스시 종류

        int startIdx = 0;  // 먹는 초밥 범위의 시작 인덱스
        int endIdx = startIdx + k - 1;  // 먹는 초밥 범위의 끝 인덱스

        // 초기 범위에 속한 초밥 종류 조사
        for (int i = startIdx; i <= endIdx; ++i) {
            if (count[sushi[i]] == 0)
                sushiTypes++;

            count[sushi[i]]++;
        }
        max = sushiTypes;

        int prevStartIdx = startIdx;
        while (startIdx < N - 1) {
            startIdx++;
            endIdx = (endIdx + 1) % N;

            // 이전 시작점 초밥이 유일한 종류였다면
            if (--count[sushi[prevStartIdx]] == 0)
                sushiTypes--;

            // 새로 먹게된 초밥이 새로운 종류라면
            if (++count[sushi[endIdx]] == 1)
                sushiTypes++;

            //범위 내에 이벤트 초밥이 없다면 종류 1 추가 가능
            if (count[c] == 0)
                max = Math.max(sushiTypes + 1, max);
            else
                max = Math.max(sushiTypes, max);

            prevStartIdx = startIdx;
        }

        System.out.println(max);
    }
}
