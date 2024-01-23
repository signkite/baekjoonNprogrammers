import java.io.*;

public class Main {
    static int[][] board = new int[19][19];
    static int[][] move = {{-1, 1}, {0, 1}, {1, 1}, {1, 0}};
    static int winner, winPosX, winPosY;

    public static boolean validCoord(int x, int y) {
        return (0 <= x && x < 19) && (0 <= y && y < 19);
    }

     // target 에 해당하는 번호가 이겼는지 판단하는 매서드
    public static boolean isWin(int target) {
        // 왼쪽 위 -> 오른쪽 아래로 이동하며 북동, 동, 남동, 남 4개 방향에 대해
        // 현재 지점이 끝부분인지 판단한다.
        int x, y, dx, dy, nx, ny;
        int seq;

        for (int r = 0; r < 19; ++r) {
            for (int c = 0; c < 19; ++c) {
                if (board[r][c] == target) {
                    x = r;
                    y = c;

                    // 네 방향에 대해 끝부분이라면 연속된 길이 파악하여 이겼는지 여부를 조사한다.
                    for (int i = 0; i < 4; ++i) {
                        seq = 1;
                        dx = move[i][0];
                        dy = move[i][1];

                        // 이동할 부분의 반대 지점을 확인하여 끝부분인지 확인한다.
                        nx = x - dx;
                        ny = y - dy;
                        if (validCoord(nx, ny) && board[nx][ny] == target)
                            continue;

                        // 끝부분이라면 해당 방향에 대해 길이를 확인한다.
                        nx = x + dx;
                        ny = y + dy;
                        while (validCoord(nx, ny) && board[nx][ny] == target) {
                            seq++;
                            nx += dx;
                            ny += dy;
                        }

                        if (seq == 5) {
                            winner = target;
                            winPosX = x + 1;
                            winPosY = y + 1;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 19; ++i) {
            String[] line = br.readLine().split(" ");
            for(int j = 0; j < 19; ++j) {
                board[i][j] = Integer.parseInt(line[j]);
            }
        }

        if (!isWin(1))
            isWin(2);

        System.out.println(winner);

        if (winner != 0)
            System.out.println(winPosX + " " + winPosY);
    }
}