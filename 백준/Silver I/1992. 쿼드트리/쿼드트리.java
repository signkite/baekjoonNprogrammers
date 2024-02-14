import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[][] img;

    public static String quadTree(int n, int r, int c) {
        if (n == 1)
            return Character.toString(img[r][c]);

        int half = n / 2;
        String child1 = quadTree(half, r, c);
        String child2 = quadTree(half, r, c + half);
        String child3 = quadTree(half, r + half, c);
        String child4 = quadTree(half, r + half, c + half);

        // 자식으로부터 전달받은 숫자가 모두 동일하다면 해당 숫자를 반환
        if (child1.length() == 1 && child1.equals(child2) && child2.equals(child3) && child3.equals(child4))
            return child1;

        // 자식이 모두 동일하지 않다면 자식을 조합한 문자열을 반환
        else
            return "(" + child1 + child2 + child3 + child4 + ")";

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        img = new char[N][N];
        
        for (int i = 0; i < N; ++i) {
            String line = br.readLine();
            for (int j = 0; j < N; ++j) {
                img[i][j] = line.charAt(j);
            }
        }

        System.out.println(quadTree(N, 0, 0));
    }
}