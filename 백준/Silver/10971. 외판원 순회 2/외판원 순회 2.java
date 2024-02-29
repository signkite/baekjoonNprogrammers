import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int[][] adjMat;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        adjMat = new int[N][N];
        dp = new int[N][1 << (N - 1)];

        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                adjMat[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dist(0, (1 << (N - 1)) - 1));
    }

    // dist(cur, mask) :
    // cur 번 정점으로부터 mask에 속한 정점을 모두 순회한 후
    // 0 번 정점으로 돌아가는 최소 비용
    public static int dist(int cur, int mask) {
        // 저장된 값이 있다면 해당 값 사용
        if (dp[cur][mask] != 0)
            return dp[cur][mask];

        // 기저 조건
        if (mask == 0) {
            if (adjMat[cur][0] == 0) {
                dp[cur][0] = Integer.MAX_VALUE;
            } else {
                dp[cur][0] = adjMat[cur][0];
            }

            return dp[cur][0];
        }

        // 저장된 값이 없다면 재귀로 값 찾기
        int minVal = Integer.MAX_VALUE;
        for (int i = 1; i < N; ++i) {
            if ((mask & 1 << (i - 1)) == 0) continue;

            // cur에서 i로 갈 수 없는 경우
            if (adjMat[cur][i] == 0) continue;

            int subDist = dist(i, mask & ~(1 << (i - 1)));

            // i 에서 i를 제외한 mask의 정점을 모두 거쳐 0 번 정점으로 갈 수 없는경우
            if (subDist == Integer.MAX_VALUE) continue;

            int curDist = adjMat[cur][i] + subDist;
            if (minVal > curDist) {
                minVal = curDist;
            }
        }

        return dp[cur][mask] = minVal;
    }

}