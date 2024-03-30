import java.io.*;
import java.util.*;

public class Solution {
    static int T;
    static int N, W, H;
    static int[][] map;

    static int totalBricks;  // 총 벽돌 개수
    static int maxCrash;  // 최대 깨뜨린 벽돌의 개수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; ++t) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            totalBricks = 0;
            maxCrash = 0;

            // 맵 입력
            map = new int[H][W];
            for (int i = 0; i < H; ++i) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; ++j) {
                    map[i][j] = Integer.parseInt(st.nextToken());

                    if (map[i][j] > 0) totalBricks++;
                }
            }

            game(0, 0);
            System.out.printf("#%d %d\n", t, totalBricks - maxCrash);
        }
    }

    // idx번째 구슬을 떨어뜨리는 작업을 하는 함수.
    // 모든 구슬을 소진하면 그 개수를 세어 max count에 반영한다.
    public static void game(int idx, int crash) {
        if (idx == N) {
            maxCrash = Math.max(crash, maxCrash);
            return;
        }

        int[][] temp = mapCopy(map);

        boolean isFinish = true;
        for (int i = 0; i < W; ++i) {
            if (map[H - 1][i] == 0) continue; // i 열에 터뜨릴 블럭이 없는 경우

            isFinish = false;
            game(idx + 1, crash + crashBricks(i));
            map = mapCopy(temp);
        }

        // N 개의 구슬을 사용하지 않았지만, 이미 모든 블록이 제거된 경우
        if (isFinish) maxCrash = Math.max(crash, maxCrash);
    }


    // col 행 위치에 구슬을 떨어뜨려 처리 후, 그 개수를 반환한다.
    public static int crashBricks(int col) {
        int x = 0;
        int y = col;
        int count = 0;
        boolean[][] checked = new boolean[H][W];  // 터뜨릴 위치 표시

        while (map[x][y] == 0) x++;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {x, y});
        checked[x][y] = true;

        while (!q.isEmpty()) {
            x = q.peek()[0];
            y = q.poll()[1];
            count++;

            int num = map[x][y];

            for (int i = -num + 1; i <= num - 1; ++i) {
                if (validCoord(x + i, y) && !checked[x + i][y] && map[x + i][y] != 0) {
                    checked[x + i][y] = true;
                    q.offer(new int[] {x + i, y});
                }

                if (validCoord(x, y + i) && !checked[x][y + i] && map[x][y + i] != 0) {
                    checked[x][y + i] = true;
                    q.offer(new int[] {x, y + i});
                }
            }
        }

        gravity(checked);

        return count;
    }

    // 깨뜨릴 위치가 표시된 checked 배열을 받아, 블록을 실제로 깨뜨리고 중력을 적용하는 함수
    public static void gravity(boolean[][] checked) {

        // check[i][j] 표시된 블록 0으로 변경
        for (int i = 0; i < H; ++i) {
            for (int j = 0; j < W; ++j) {
                if (checked[i][j]) map[i][j] = 0;
            }
        }

        // 한 열씩 움직이며 중력 적용
        for (int j = 0; j < W; ++j) {
            int i = H - 1;
            int space = 0;

            while (i >= 0) {
                if (map[i][j] == 0)
                    space++;
                else if (space != 0) {
                    map[i + space][j] = map[i][j];
                    map[i][j] = 0;
                }
                i--;
            }
        }
    }

    public static int[][] mapCopy(int[][] original) {
        int[][] copy = new int[H][W];

        for (int i = 0; i < H; ++i) {
            for (int j = 0; j < W; ++j) {
                copy[i][j] = original[i][j];
            }
        }

        return copy;
    }

    public static boolean validCoord(int x, int y) {
        return 0 <= x && x < H && 0 <= y && y < W;
    }
}