import java.io.*;
import java.util.*;

public class Solution {
	static int T;
	static int N;
	static int[] X, Y;
	static double E;
	static int[] parents;
	
	static class Edge {
		int v1, v2;
		long value;
		
		Edge(int v1, int v2, long value) {
			this.v1 = v1;
			this.v2 = v2;
			this.value = value;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; ++t) {
			N = Integer.parseInt(br.readLine());
			
			X = new int[N];
			Y = new int[N];
			parents = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; ++i) {
				X[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; ++i) {
				Y[i] = Integer.parseInt(st.nextToken());
			}
			
			E = Double.parseDouble(br.readLine());
			List<Edge> edges = new ArrayList<>();
			
			// 간선의 가중치: 해저터널 길이의 제곱
			for (int i = 0; i < N - 1; ++i) {
				for (int j = i + 1; j < N; ++j) {
					int x = X[i] - X[j];
					int y = Y[i] - Y[j];
					edges.add(new Edge(i, j, (long) x * x + (long) y * y));
				}
			}
			
			// 가중치를 기준으로 오름차순 정렬
			Collections.sort(edges, (e1, e2) -> {
				return e1.value >= e2.value ? 1 : -1;
			});
			
			// union find를 위해 각 정점 초기화
			for (int i = 0; i < N; ++i) {
				parents[i] = i;
			}
			
			int connect = 0;
			long lengthSquare = 0;
			for (int i = 0, size = edges.size(); i < size; ++i) {
				if (connect == N - 1)
					break;
				
				Edge e = edges.get(i);
				
				if (find(e.v1) == find(e.v2))
					continue;
				
				union(e.v1, e.v2);
				lengthSquare += e.value;
				connect++;
			}
			
			System.out.printf("#%d %d\n", t, Math.round(lengthSquare * E));
		}
	}
	
	public static int find(int x) {
		if (x == parents[x]) return x;
		return parents[x] = find(parents[x]);
	}
	
	public static void union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);
		
		if (rootX != rootY)
			parents[rootX] = rootY;
	}
}