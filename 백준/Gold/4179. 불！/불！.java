import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static char[][] maze;
    static Deque<int[]> jihoon = new ArrayDeque<>();
    static Deque<int[]> fires = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        maze = new char[R][C];

        // 미로를 입력 받으며 지훈이 위치, 불 위치 저장
        String line;
        char c;
        for (int i = 0; i < R; ++i) {
            line = br.readLine();

            for (int j = 0; j < C; ++j) {
                c = line.charAt(j);

                if (c == 'J')
                    jihoon.add(new int[] {i, j});
                else if (c == 'F')
                    fires.add(new int[] {i, j});

                maze[i][j] = c;
            }
        }

        int curTime = 0;
        int[] coord;
        int nx, ny;
        boolean isPossible = false;

        outer:
        while (!jihoon.isEmpty()) {
            curTime += 1;

            // 불이 번지는 것을 지도에 표시
            for (int i = 0, size = fires.size(); i < size; ++i) {
                coord = fires.removeFirst();

                for(int j = 0; j < 4; ++j) {
                    nx = coord[0] + dx[j];
                    ny = coord[1] + dy[j];

                    if (nx < 0 || R <= nx) continue;
                    if (ny < 0 || C <= ny) continue;
                    if (maze[nx][ny] == '#' || maze[nx][ny] == 'F') continue;

                    maze[nx][ny] = 'F';
                    fires.add(new int[] {nx, ny});
                }
            }

            // 지훈이가 불을 피해서 갈 수 있는 모든 경우를 확인
            for (int i = 0, size = jihoon.size(); i < size; ++i) {
                coord = jihoon.removeFirst();

                // 가장자리에 도달할 수 있음이 확인되면 정답 도출
                if (coord[0] == 0 || coord[0] == R - 1 || coord[1] == 0 || coord[1] == C - 1) {
                    isPossible = true;
                    break outer;
                }

                for(int j = 0; j < 4; ++j) {
                    nx = coord[0] + dx[j];
                    ny = coord[1] + dy[j];

                    if (nx < 0 || R <= nx) continue;
                    if (ny < 0 || C <= ny) continue;
                    
                    // 지훈이가 갈 수 있는 곳으로 이미 확인한 길은 J로 표시
                    if (maze[nx][ny] == '.') {
                        maze[nx][ny] = 'J';
                        jihoon.add(new int[] {nx, ny});
                    }
                }
            }
        }

        if (isPossible)
            System.out.println(curTime);
        else
            System.out.println("IMPOSSIBLE");
    }
}