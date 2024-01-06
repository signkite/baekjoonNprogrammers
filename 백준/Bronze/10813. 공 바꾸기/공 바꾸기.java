import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] line;

        line = bf.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);

        int[] balls = new int[N];
        for (int x = 0; x < N; ++x) {
            balls[x] = x + 1;
        }

        int i, j, tmp;
        for (int x = 0; x < M; ++x) {
            line = bf.readLine().split(" ");
            i = Integer.parseInt(line[0]);
            j = Integer.parseInt(line[1]);
            tmp = balls[i - 1];
            balls[i - 1] = balls[j - 1];
            balls[j - 1] = tmp;
        }

        for (int num: balls) {
            System.out.print(num + " ");
        }
    }
}