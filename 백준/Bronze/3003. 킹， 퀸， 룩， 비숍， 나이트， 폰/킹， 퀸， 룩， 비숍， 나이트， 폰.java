import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] rightNum = {1, 1, 2, 2, 2, 8};

        for (int i = 0; i < 6; ++i) {
            int result = rightNum[i] - Integer.parseInt(st.nextToken());
            System.out.print(result + " ");
        }
    }
}