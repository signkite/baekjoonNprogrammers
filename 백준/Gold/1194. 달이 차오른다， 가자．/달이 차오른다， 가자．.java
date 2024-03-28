import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static boolean[][][] check;  // [행][열][열쇠 비스마스크]
    static char[][] maze;
    static int[] start = new int[2];

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maze = new char[N][M];
        check = new boolean[N][M][64];
        for (int i = 0; i < N; ++i) {
            String line = br.readLine();
            for (int j = 0; j < M; ++j) {
                maze[i][j] = line.charAt(j);

                // {행, 열, 가진 열쇠 비트마스킹 표현}
                if (maze[i][j] == '0') start = new int[] {i, j, 0, 0};
            }
        }

        int answer = -1;

        Queue<int[]> q = new ArrayDeque<>();
        check[start[0]][start[1]][0] = true;
        q.offer(start);

        outer:
        while (!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.peek()[1];
            int keys = q.peek()[2];
            int move = q.poll()[3];

            for (int i = 0; i < 4; ++i) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (invalidCoord(nx, ny)) continue;
                if (maze[nx][ny] == '#') continue;
                if (check[nx][ny][keys]) continue;
                if (isLock(maze[nx][ny]) && !canOpen(maze[nx][ny], keys)) continue; // 자물쇠를 열 수 없는 경우

                // 도착지에 도달한 경우
                if (maze[nx][ny] == '1') {
                    answer = move + 1;
                    break outer;
                }

                // 열쇠 획득시 새로운 keys로 진행
                if (isKey(maze[nx][ny])) {
                    check[nx][ny][keys] = true;

                    int newKeys = keys | 1 << (maze[nx][ny] - 'a');
                    check[nx][ny][newKeys] = true;
                    q.offer(new int[] {nx, ny, newKeys, move + 1});
                }

                // 열쇠가 아닌 경우 그냥 진행
                else {
                    check[nx][ny][keys] = true;
                    q.offer(new int[] {nx, ny, keys, move + 1});
                }
            }
        }

        System.out.println(answer);
    }

    public static boolean invalidCoord(int x, int y) {
        return x < 0 || N <= x || y < 0 || M <= y;
    }

    public static boolean isLock(char mazeChar) {
        return 'A' <= mazeChar && mazeChar <= 'F';
    }

    public static boolean isKey(char mazeChar) {
        return 'a' <= mazeChar && mazeChar <= 'f';
    }

    public static boolean canOpen(char lock, int keys) {
        return (keys & 1 << (lock - 'A')) != 0;
    }
}