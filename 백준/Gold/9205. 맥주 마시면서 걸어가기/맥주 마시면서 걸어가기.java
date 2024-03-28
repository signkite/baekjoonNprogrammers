import java.io.*;
import java.util.*;

class Main {
    static int t, n;
    static int[][] coord;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; ++tc) {
            // 좌표 입력받기
            // 상근이 집 인덱스: 0
            // 페스티벌 장소 인덱스: n + 1
            n = Integer.parseInt(br.readLine());
            coord = new int[n + 2][2];
            for (int idx = 0; idx < n + 2; ++idx) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                coord[idx] = new int[] {x, y};
            }

            // 좌표를 기반으로 map 생성 (두 좌표의 거리가 1000 이하라면 행복히 갈 수 있는 거리이므로 true로 표시)
            map = new boolean[n + 2][n + 2];
            for (int i = 0; i < n + 2; ++i) {
                map[i][i] = true;
                for (int j = 0; j < n + 2; ++j) {
                    int d = dist(coord[i], coord[j]);

                    map[j][i] = map[i][j] = d <= 1000;
                }
            }

            // {0 ~ k} 의 정점을 경유할 수 있는 상태에서, i에서 j로 행복하게 갈 수 있는지 체크
            for (int k = 0; k < n + 2; ++k) {
                for (int i = 0; i < n + 2; ++i) {
                    for (int j = 0; j < n + 2; ++j) {
                        if (map[i][j]) continue;

                        map[i][j] = map[i][k] && map[k][j];
                    }
                }
            }

            System.out.println(map[0][n + 1] ? "happy" : "sad");
        }
    }

    // c1과 c2 두 좌표의 맨해튼 거리 반환
    public static int dist(int[] c1, int[] c2) {
        return Math.abs(c1[0] - c2[0]) + Math.abs(c1[1] - c2[1]);
    }
}