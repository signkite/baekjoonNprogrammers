import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] paper = new int[100][100];
        int N = Integer.parseInt(br.readLine());

        String[] line;
        for (int i = 0; i < N; ++i) {
            line = br.readLine().split(" ");

            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);

            for (int dx = 0; dx < 10; ++dx) {
                for (int dy = 0; dy < 10; ++dy) {
                    paper[x + dx][y + dy] = 1;
                }
            }
        }

        int area = 0;
        for (int i = 0; i < 100; ++i) {
            for (int j = 0; j < 100; ++j) {
                if (paper[i][j] == 1)
                    area++;
            }
        }

        System.out.println(area);
    }
}