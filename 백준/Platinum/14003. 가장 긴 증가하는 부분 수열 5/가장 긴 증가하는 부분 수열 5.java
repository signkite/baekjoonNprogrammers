import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int[] lis = new int[N];
        int[] record = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        final int INF = (int) 1e9 + 1;
        Arrays.fill(lis, INF);
        lis[0] = arr[0];
        record[0] = 0;
        int idx = 0;
        for (int i = 1; i < N; i++) {
            if (arr[i] > lis[idx]) {
                lis[++idx] = arr[i];
                record[i] = idx;
            } else {
                int jdx = Arrays.binarySearch(lis, arr[i]);
                if (jdx < 0) {
                    jdx = Math.abs(jdx) - 1;
                }
                lis[jdx] = arr[i];
                record[i] = jdx;
            }
        }

        System.out.println(idx+1); // the length of LIS

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = N-1; i >= 0; i--) {
            if (record[i] == idx) {
                stack.push(arr[i]);
                idx--;
            }
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}