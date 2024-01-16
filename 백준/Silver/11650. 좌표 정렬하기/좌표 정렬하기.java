import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line;
        int N = Integer.parseInt(br.readLine());
        int[][] points = new int[N][2];
        for (int i = 0; i < N; ++i) {
            line = br.readLine().split(" ");
            points[i][0] = Integer.parseInt(line[0]);
            points[i][1] = Integer.parseInt(line[1]);
        }

        Arrays.sort(points, (p1, p2) -> {
            if (p1[0] == p2[0])
                return p1[1] - p2[1];
            else
                return p1[0] - p2[0];
        });

        for (int i = 0; i < N; ++i) {
            System.out.println(points[i][0] + " " + points[i][1]);
        }
    }
}