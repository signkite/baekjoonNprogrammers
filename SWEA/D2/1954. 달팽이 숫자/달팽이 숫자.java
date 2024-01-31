import java.io.*;

class Solution {
    public static void main(String[] args) throws IOException {
        // 우, 하, 좌, 상 순으로 이동을 구현
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; ++t) {
            int N = Integer.parseInt(br.readLine());
            int[][] snail = new int[N][N];
            int direction = 0;
            int num = 1;

            if (N == 1) {
                System.out.println("#" + t);
                System.out.println(1);
                continue;
            }

            int x = 0, y = 0;
            while (snail[x][y] == 0) {

                // 현재 direction 으로 바라보는 곳이 0인 곳을 쭉 채운다.
                while (0 <= x && x < N && 0 <= y && y < N && snail[x][y] == 0) {
                    snail[x][y] = num++;
                    x += dx[direction];
                    y += dy[direction];
                }
                x -= dx[direction];
                y -= dy[direction];

                direction = (direction + 1) % 4;
                x += dx[direction];
                y += dy[direction];
            }

            System.out.println("#" + t);
            for (int[] line: snail) {
                for (int n: line) {
                    System.out.print(n + " ");
                }
                System.out.println();
            }
        }
    }
}