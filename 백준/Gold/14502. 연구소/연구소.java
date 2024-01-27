import java.util.*;
import java.io.*;

public class Main {
    static int[][] map;
    static int N, M;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static List<Integer[]> virusCoords = new ArrayList<>();  // map에 초기 바이러스가 존재하는 좌표
    static Deque<Integer[]> dq;
    static int minVirus = 64;

    // 바이러스가 퍼진 영역의 최소값을 찾아 갱신하는 함수
    public static void getMinVirusArea() {
        boolean[][] visit = new boolean[N][M];
        int virus = virusCoords.size();

        dq = new ArrayDeque<>(virusCoords);
        Integer[] coord;
        int x, y, nx, ny;
        while (!dq.isEmpty()) {
            coord = dq.pollFirst();
            x = coord[0];
            y = coord[1];

            for (int i =0; i < 4; ++i) {
                nx = x + dx[i];
                ny = y + dy[i];

                if (nx < 0 || N <= nx) continue;
                if (ny < 0 || M <= ny) continue;
                if (visit[nx][ny] || map[nx][ny] != 0) continue;

                visit[nx][ny] = true;
                dq.add(new Integer[] {nx, ny});
                virus++;

                if (virus >= minVirus) return;
            }
        }
        minVirus = virus;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        // map 입력과 함께 초기 바이러스의 위치, 벽 개수 구하기
        // N * M - (최소 바이러스 개수) - 벽 개수 - (새로 세운 벽 개수) = 최대 안전 영역 개수
        int walls = 0;
        map = new int[N][M];
        for (int i = 0; i < N; ++i) {
            line = br.readLine().split(" ");
            for (int j = 0; j < M; ++j) {
                map[i][j] = Integer.parseInt(line[j]);

                if (map[i][j] == 2)
                    virusCoords.add(new Integer[] {i, j});
                else if (map[i][j] == 1)
                    walls++;
            }
        }

        // 벽 3개를 세우고 바이러스가 퍼지는 영역의 최소값을 갱신
        for (int i = 0; i < N * M - 2; ++i) {
            if (map[i / M][i % M] != 0) continue;
            map[i / M][i % M] = 1;

            for (int j = i + 1; j < N * M - 1; ++j) {
                if (map[j / M][j % M] != 0) continue;
                map[j / M][j % M] = 1;

                for (int k = j + 1; k < N * M; ++k) {
                    if (map[k / M][k % M] != 0) continue;

                    map[k / M][k % M] = 1;
                    getMinVirusArea();
                    map[k / M][k % M] = 0;
                }
                map[j / M][j % M] = 0;
            }
            map[i / M][i % M] = 0;
        }

        System.out.println(M * N - minVirus - walls - 3);
    }
}