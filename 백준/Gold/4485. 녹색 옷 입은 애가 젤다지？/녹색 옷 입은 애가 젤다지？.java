import java.io.*;
import java.util.*;

class Main {
	static int N;
	static int[][] map;
	static int[][] minMap;
	static boolean[][] visit;
	
	static final int INF = Integer.MAX_VALUE;
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = 1;
        
        while ((N = Integer.parseInt(br.readLine())) != 0) {
        	map = new int[N][N];
        	minMap = new int[N][N];  // 현재까지 경로를 살피며 만난 도둑루피 최소값을 저장
        	visit = new boolean[N][N];
        	
        	for (int i = 0; i < N; ++i) {
        		for (int j = 0; j < N; ++j) {
        			minMap[i][j] = INF;
        		}
        	}
        	
        	for (int i = 0; i < N; ++i) {
        		StringTokenizer st = new StringTokenizer(br.readLine());
        		for (int j = 0; j < N; ++j) {
        			map[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}
        	
        	dijkstra();
        	
        	System.out.println("Problem " + testCase + ": " + minMap[N-1][N-1]);
        	testCase++;
        }
    }
    
    // [N - 1][N - 1] 까지 가는 최소 경로를 다익스트라를 통해 구한다.
    public static int dijkstra() {
    	PriorityQueue<int[]> pq = new PriorityQueue<>((item1, item2) -> {
    		return item1[2] - item2[2];
    	});
    	pq.offer(new int[] {0, 0, map[0][0]});
    	
    	minMap[0][0] = map[0][0];
    	
    	while (!pq.isEmpty()) {
    		int r = pq.peek()[0];
    		int c = pq.peek()[1];
    		int dist = pq.poll()[2];
    		
    		visit[r][c] = true;
    		
    		if (r == N - 1 && c == N - 1) return dist;
    		
    		for (int i = 0; i < 4; ++i) {
    			int nr = r + dr[i];
    			int nc = c + dc[i];
    			
    			if (!validCoord(nr, nc)) continue;
    			if (visit[nr][nc]) continue;
    			if (minMap[nr][nc] <= map[nr][nc] + dist) continue;
    			
    			minMap[nr][nc] = map[nr][nc] + dist;
    			pq.offer(new int[] {nr, nc, minMap[nr][nc]});
    		}
    	}
    	
    	return 0;
    }
    
    public static boolean validCoord(int r, int c) {
    	return 0 <= r && r < N && 0 <= c && c < N;
    }
}