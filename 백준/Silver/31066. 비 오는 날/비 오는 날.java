import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        int N, M, K;
        for (int i = 0; i < T; ++i) {
            String[] line = bf.readLine().split(" ");
            N = Integer.parseInt(line[0]);
            M = Integer.parseInt(line[1]);
            K = Integer.parseInt(line[2]);
            
            // 불가능한 경우 체크
            if (M == 1 && K == 1 && N > 1) {
                System.out.println(-1);
                continue;
            }
            
            // 횟수 체크
            int count = 1;
            N -= K * M;
            while (N > 0) {
                // 한 번에 갈 수 없는 경우
                // 한 명이 모든 우산을 가지고 나머지를 데리러 왕복
                N -= K * M - 1;
                count += 2;
            }
            System.out.println(count);
        }

    }
}