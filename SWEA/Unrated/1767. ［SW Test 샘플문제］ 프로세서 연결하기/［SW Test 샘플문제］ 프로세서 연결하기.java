import java.io.*;
import java.util.*;

public class Solution {
    static int T, N;
    static int[][] processor;
    static List<int[]> cores;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static int maxCoreCount;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; ++t) {
            N = Integer.parseInt(br.readLine());
            processor = new int[N][N];
            cores = new ArrayList<>();

            // 입력 처리
            for (int i = 0; i < N; ++i) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int j = 0; j < N; ++j) {
                    processor[i][j] = Integer.parseInt(st.nextToken());

                    // 전기 공급이 필요한 코어만 고려
                    if (processor[i][j] == 1) {
                        if (0 < i && i < N - 1 && 0 < j && j < N - 1)
                            cores.add(new int[] {i, j});
                    }
                }
            }

            // 전원이 연결되지 않은 core들을 각각 4방향으로 연결해보며 정답 도출
            answer = Integer.MAX_VALUE;
            maxCoreCount = 0;
            search(0, 0, 0);

            System.out.printf("#%d %d\n", t, answer);
        }
    }

    public static void search(int idx, int connected, int len) {

        // 현재까지 연결된 core 개수가 최대 개수인 경우 정답 갱신
        if (connected > maxCoreCount) {
            maxCoreCount = connected;
            answer = len;
        } else if (connected == maxCoreCount) {
            answer = Math.min(answer, len);
        }

        // 기저조건
        if (idx == cores.size())
            return;

        int[] cur = cores.get(idx);

        // 현재 코어를 연결해보는 경우
        for (int i = 0; i < 4; ++i) {
            if (!canConnect(cur[0], cur[1], i))
                continue;

            search(idx + 1, connected + 1, len + connect(cur[0], cur[1], i));
            cut(cur[0], cur[1], i);
        }

        // 현재 코어를 연결하지 않고 다음 코어를 살펴보는 경우
        search(idx + 1, connected, len);
    }

    public static boolean canConnect(int x, int y, int d) {
        while (validCoord(x += dx[d], y += dy[d]))
            if (processor[x][y] == 1)
                return false;

        return true;
    }

    // x행 y열에서 d 방향으로 전선을 연결하고 길이를 반환
    public static int connect(int x, int y, int d) {
        int len = 0;

        while (validCoord(x += dx[d], y += dy[d])) {
            processor[x][y] = 1;
            len++;
        }

        return len;
    }

    // x행 y열에서 d 방향으로 전선 연결을 해제
    public static void cut(int x, int y, int d) {
        while (validCoord(x += dx[d], y += dy[d]))
            processor[x][y] = 0;
    }

    public static boolean validCoord(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}