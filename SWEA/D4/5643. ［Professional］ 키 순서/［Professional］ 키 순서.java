import java.io.*;
import java.util.*;

class Solution {
	static int T;
	static int N, M;
	static Set<Integer>[] bigger;  // bigger[x] : x가 알고있는 자신보다 큰 사람들 번호
	static Set<Integer>[] smaller;  // smaller[x] : x가 알고있는 자신보다 작은 사람들 번호
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; ++t) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			bigger = new HashSet[N + 1];
			smaller = new HashSet[N + 1];
			
			// 집합 초기화
			for (int i = 1; i <= N; ++i) {
				bigger[i] = new HashSet<>();
				smaller[i] = new HashSet<>();
			}
			
			// 관계 처리
			for (int i = 0; i < M; ++i) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken()); 
				int b = Integer.parseInt(st.nextToken());
				
				// 서로의 존재를 알게됨
				bigger[a].add(b);
				smaller[b].add(a);
				
				// a가 알고있는 자신보다 작은 사람들에게 b의 존재를 알린다. b또한 이들을 알게된다.
				for (int small: smaller[a]) {
					bigger[small].add(b);
					smaller[b].add(small);
				}
				
				// b가 알고있는 자신보다 큰 사람들에게 a의 존재를 알린다. a또한 이들을 알게된다.
				for (int big: bigger[b]) {
					smaller[big].add(a);
					bigger[a].add(big);
				}
			}
			
			// (알고있는, 자신보다 작은 사람 수) + (알고있는, 자신보다 큰 사람 수) == N - 1 이면
			// 몇번째인지 알 수 있음
			int answer = 0;
			for (int i = 1; i <= N; ++i) {
				if (smaller[i].size() + bigger[i].size() == N - 1)
					answer++;
			}
			
			System.out.printf("#%d %d\n", t, answer);
		}
		
	}
}