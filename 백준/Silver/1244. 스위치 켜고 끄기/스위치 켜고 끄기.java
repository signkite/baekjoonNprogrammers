import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            if (st.nextToken().equals("1")) {
                arr[i] = 1;
            }
        }

        int student = Integer.parseInt(br.readLine());
        for (int i = 0; i < student; ++i) {
            String[] line = br.readLine().split(" ");
            int num = Integer.parseInt(line[1]);

            // 남학생
            if (line[0].equals("1")) {
                for (int j = 1; j <= N; ++j) {
                    if (j % num == 0) {
                        arr[j] = Math.abs(arr[j] - 1);
                    }
                }
            }

            // 여학생
            else {
                int n = 0;
                while (1 <= num - n && num + n <= N && arr[num - n] == arr[num + n]) {
                    n++;
                }
                n--;
                for (int j = num - n; j <= num + n; ++j) {
                    arr[j] = Math.abs(arr[j] - 1);
                }
            }
        }

        for (int i = 1; i <= N; ++i) {
            System.out.print(arr[i] + " ");

            if (i % 20 == 0)
                System.out.println();
        }
    }
}