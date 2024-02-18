import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] num = new int[100001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            int n = Integer.parseInt(st.nextToken());
            num[n] += 1;
        }

        // 가장 근무일 수가 많은 병사가
        // 두 번째로 많은 병사보다 2일 이상 많지만 않으면 됨
        Arrays.sort(num);

        int sumOfOthers = 0;
        for (int i = 99999; i > 0; --i)
            sumOfOthers += num[i];

        if (num[100000] > sumOfOthers + 1)
            System.out.println("NO");
        else
            System.out.println("YES");
    }
}