import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[][] paper = new int[100][100];
        int x, y;
        String[] line;
        for (int i = 0; i < n; ++i) {
            line = bf.readLine().split(" ");
            x = Integer.parseInt(line[0]);
            y = Integer.parseInt(line[1]);

            // 색종이를 붙인 곳의 좌표의 수를 증가시킨다
            for (int j = 0; j < 10; ++j) {
                for (int k = 0; k < 10; ++k) {
                    paper[x + j][y + k]++;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < 100; ++i) {
            for (int j = 0; j < 100; ++j) {
                if (paper[i][j] > 0) {
                   answer++;
                }
            }
        }

        System.out.println(answer);
    }
}