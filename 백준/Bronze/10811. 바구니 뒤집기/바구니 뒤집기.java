import java.io.*;

public class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");

        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);

        int[] arr = new int[N];
        for (int i = 0; i < N; ++i)
            arr[i] = i + 1;

        int temp;
        for (int i = 0; i < M; ++i) {
            line = br.readLine().split(" ");
            int start = Integer.parseInt(line[0]) - 1;
            int end = Integer.parseInt(line[1]) - 1;
            for (int j = 0; j <= (end - start) / 2; ++j) {
                temp = arr[start + j];
                arr[start + j] = arr[end - j];
                arr[end - j] = temp;
            }
        }

        for (int num: arr) {
            System.out.print(num + " ");
        }
    }
}