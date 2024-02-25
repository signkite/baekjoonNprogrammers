import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int numberOfIsland;
    static int[][] bridge;
    static int totalLen;
    static int[] parents;

    static class Edge implements Comparable<Edge> {
        int v1, v2;
        int length;

        Edge(int v1, int v2, int length) {
            this.v1 = v1;
            this.v2 = v2;
            this.length = length;
        }

        @Override
        public int compareTo(Edge anotherEdge) {
            if (this.length == anotherEdge.length)
                return 0;
            else
                return this.length - anotherEdge.length;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 지도 입력받기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // BFS로 섬의 개수를 세며 섬마다 고유 번호를 붙인다.
        islandNumbering();

        // bridge[a][b] == a 에서 b 로 가는 최소 다리 길이
        // 다리 길이가 1 이하인 경우 두 섬간에는 다리를 못짓는다는 뜻이다.
        bridge = new int[numberOfIsland + 1][numberOfIsland + 1];
        getBridgeMatrix();

//        // for debug
//        for (int[] line: map) {
//            System.out.println(Arrays.toString(line));
//        }
//
//        // for debug
//        System.out.println("--------------");
//        for (int[] line: bridge) {
//            System.out.println(Arrays.toString(line));
//        }

        // 크루스칼 알고리즘으로 MST 구하기
        parents = new int[numberOfIsland + 1];
        for (int i = 1; i <= numberOfIsland; ++i) {
            parents[i] = i;
        }

        List<Edge> edges = new ArrayList<>();
        for (int i = 1; i < numberOfIsland; ++i) {
            for (int j = i + 1; j <= numberOfIsland; ++j) {
                if (bridge[i][j] == 0) continue;

                edges.add(new Edge(i, j, bridge[i][j]));
            }
        }

        Collections.sort(edges);

        int edgeCount = 0;
        for (int i = 0, size = edges.size(); i < size; ++i) {
            Edge cur = edges.get(i);

            if (find(cur.v1) == find(cur.v2)) continue;

            totalLen += cur.length;

            union(cur.v1, cur.v2);

            edgeCount++;
            if(edgeCount >= numberOfIsland) {
                break;
            }
        }

//        System.out.println("----------");
//        System.out.println(Arrays.toString(parents));

        int answer = totalLen;
        for (int i = 2; i <= numberOfIsland; ++i) {
            if (find(1) != find(i)) {
                answer = -1;
                break;
            }
        }
        System.out.println(answer);
    }

    public static int find(int x) {
        if (x == parents[x]) return x;
        return parents[x] = find(parents[x]);
    }

    public static void union(int x, int y) {
        int rootx = find(x);
        int rooty = find(y);

        if (rootx != rooty) parents[rootx] = rooty;
    }

    public static void islandNumbering() {
        boolean[][] visit = new boolean[N][M];  // 방문한 섬 표시
        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (map[i][j] == 0 || visit[i][j])
                    continue;

                numberOfIsland++;

                q.offer(new int[] {i, j});
                visit[i][j] = true;
                while (!q.isEmpty()) {
                    int x = q.peek()[0];
                    int y = q.poll()[1];

                    // 지도에 현재 섬 번호를 표시
                    map[x][y] = numberOfIsland;

                    for (int d = 0; d < 4; ++d) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];

                        if (!validCoord(nx, ny)) continue;
                        if (visit[nx][ny] || map[nx][ny] == 0) continue;

                        visit[nx][ny] = true;
                        q.offer(new int[] {nx, ny});
                    }
                }
            }
        }
    }

    public static void getBridgeMatrix() {
        boolean[][] visit = new boolean[N][M];  // 방문한 육지 표시
        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (map[i][j] == 0) continue;
                if (visit[i][j]) continue;  // 이미 탐색한 육지 건너뛰기

                // 육지를 bfs로 탐색하며 가장자리 육지를 만나면 다리를 지어본다.
                q.offer(new int[] {i, j});
                visit[i][j] = true;

                while (!q.isEmpty()) {
                    int x = q.peek()[0];
                    int y = q.poll()[1];

                    for (int d = 0; d < 4; ++d) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];

                        if (!validCoord(nx, ny) || visit[nx][ny])
                            continue;

                        // 땅과 인접한 바다 발견시 바다 방향으로 다리를 지어본다.
                        if (map[nx][ny] == 0)
                            tryBridging(x, y, d);

                        // 땅 발견시 q에 넣기
                        else {
                            q.offer(new int[] {nx, ny});
                            visit[nx][ny] = true;
                        }
                    }
                }
            }
        }
    }

    // islandNum 번호의 섬의 좌표 중 하나인 map[x][y] 부터 시작해
    // d 방향으로 다리를 지어보고, 지을 수 있다면 bridge를 갱신한다.
    public static void tryBridging(int x, int y, int d) {
        int islandNum = map[x][y];
        int length = 0;
        int anotherIsland = 0;

        while (true) {
            x += dx[d];
            y += dy[d];

            // d 번째 방향으로 다리를 지을 수 없다면
            if (!validCoord(x, y) || map[x][y] == islandNum)
                return;

            // 다른 섬과 닿는다면
            if (map[x][y] != 0) {
                anotherIsland = map[x][y];
                break;
            }

            // 바다라면 길이 1 추가
            length++;
        }

        // 길이 1 이하 다리는 건설 못한다.
        if (length <= 1)
            return;

        // 인접 행렬 (bridge) 갱신
        if (bridge[anotherIsland][islandNum] == 0 || bridge[anotherIsland][islandNum] > length) {
            bridge[anotherIsland][islandNum] = length;
            bridge[islandNum][anotherIsland] = length;
        }
    }

    public static boolean validCoord(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
