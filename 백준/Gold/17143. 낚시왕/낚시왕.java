import java.io.*;
import java.util.*;


class Shark {
	int s, d, z;

	public Shark(int s, int d, int z) {
		super();
		this.s = s;
		this.d = d;
		this.z = z;
	}
}

class Main {
	static int R, C, M;
	
	static Shark[][][] map;
	
	static int[] dr = {0, -1, 1, 0, 0};
	static int[] dc = {0, 0, 0, 1, -1};
	
	static int cur = 0;
	static int next = 1;
	
	static int fishKingCol = -1;
	static int totalSharkSize = 0;
	
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 상어 정보 입력
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new Shark[2][R][C];
        
        for (int i = 0; i < M; ++i) {
        	st = new StringTokenizer(br.readLine());
        	
        	int r = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	int s = Integer.parseInt(st.nextToken());
        	int d = Integer.parseInt(st.nextToken());
        	int z = Integer.parseInt(st.nextToken());
        	
        	map[0][r - 1][c - 1] = new Shark(s, d, z);
        }
        
        while (++fishKingCol < C) {
            
        	// 땅에 가장 가까운 상어 탐색
        	int r = 0;
        	while (r < R) {
        		if (map[cur][r][fishKingCol] != null) break;
        		r++;
        	}
        	
        	// 현재 열에 상어 존재시 낚는다.
        	if (r != R) {
        		totalSharkSize += map[cur][r][fishKingCol].z;
        		map[cur][r][fishKingCol] = null;
        	}
        	
        	map[next] = new Shark[R][C];
        	sharkMove();
        	swapCurNext();
        }
        
        System.out.println(totalSharkSize);
    }
    
    public static boolean validCoord(int r, int c) {
    	return 0 <= r && r < R && 0 <= c && c < C;
    }
    
    public static void sharkMove() {
    	for (int r = 0; r < R; ++r) {
    		for (int c = 0; c < C; ++c) {
    			if (map[cur][r][c] == null) continue;
    			
    			// 현재 map의 r행 c열 상어 정보 추출
    			int s = map[cur][r][c].s;
    			int d = map[cur][r][c].d;
    			int z = map[cur][r][c].z;

    			// 방향에 따라 상어의 다음 좌표 계산
    			int nr = r;
    			int nc = c;
    			int leftS = s;
    			
    			// 왕복해 원위치로 돌아오는 계산 제거
    			if (d <= 2)
    				leftS %= 2 * (R - 1);
    			else  
    				leftS %= 2 * (C - 1);
    			
    			while (leftS-- > 0) {
    				nr += dr[d];
    				nc += dc[d];
    				
    				if (nr == -1) {
    					d = 2;
    					nr = 1;
    				} else if (nr == R) {
    					d = 1;
    					nr = R - 2;
    				} else if (nc == -1) {
    					d = 3;
    					nc = 1;
    				} else if (nc == C) {
    					d = 4;
    					nc = C - 2;
    				}
    			}
    			
    			// 이동한 곳에 이미 다른 상어가 이동해있다면 크기 비교 후 식사
    			if (map[next][nr][nc] != null) {
    				if (map[next][nr][nc].z < z)
    					map[next][nr][nc] = new Shark(s, d, z);
    			} else {
    				map[next][nr][nc] = new Shark(s, d, z);
    			}
    		}
    	}
    }
    
    public static void swapCurNext() {
    	int temp = cur;
    	cur = next;
    	next = temp;
    }
}