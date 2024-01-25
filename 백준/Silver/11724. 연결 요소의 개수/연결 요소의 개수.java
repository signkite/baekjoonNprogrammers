import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);
        int answer = 0;

        // graph[x][y] : x와 y가 연결되어있다.
        boolean[][] graph = new boolean[N + 1][N + 1];

        // 정점 방문 체크
        boolean[] check = new boolean[N + 1];

        // 그래프 입력받기
        for(int i = 0; i < M; ++i) {
            line = br.readLine().split(" ");
            int u = Integer.parseInt(line[0]);
            int v = Integer.parseInt(line[1]);

            graph[u][v] = graph[v][u] = true;
        }

        // 정점 1부터 N까지 그래프를 탐색하며 연결요소 개수 파악
        Deque<Integer> dq = new ArrayDeque<>();
        int vertex;
        for(int i = 1; i <= N; ++i) {
            if (check[i]) continue;

            dq.offer(i);
            check[i] = true;

            // i 로부터 연결된 모든 정점을 파악, 연결 요소의 개수 1개 확인
            while (!dq.isEmpty()) {
                vertex = dq.pollFirst();

                for(int j = 1; j <= N; ++j) {
                    if (!graph[vertex][j] || check[j]) continue;
                    dq.offer(j);
                    check[j] = true;
                }
            }
            answer++;
        }

        System.out.println(answer);
    }
}