import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] lis = new int[N];
        Arrays.fill(lis, (int) 1e9);
        lis[0] = arr[0];
        int idx = 0;
        for (int i = 1; i < N; i++) {
            if (arr[i] > lis[idx]) {
                lis[++idx] = arr[i];
            } else {
                int jdx = binarySearch(lis, arr[i]);
                lis[jdx] = arr[i];
            }
        }
        long ans = Arrays.stream(lis).filter(i -> i != (int) 1e9).count();
        System.out.println(ans);

    }

    private static int binarySearch(int[] lis, int target) {
        int low = 0;
        int high = lis.length - 1;
        int res = lis.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (lis[mid] >= target) {
                high = mid - 1;
                res = Math.min(mid, res);
            } else {
                low = mid + 1;
            }
        }
        return res;
    }
}