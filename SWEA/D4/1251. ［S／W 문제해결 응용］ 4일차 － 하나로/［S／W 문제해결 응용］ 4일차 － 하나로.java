import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
	int target;
	double weight;
	
	Edge (int target, double weight) {
		this.target = target;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Edge anotherEdge) {
		if (this.weight == anotherEdge.weight)
			return 0;
		else
			return this.weight > anotherEdge.weight ? 1 : -1;
	}
}

public class Solution {
	static int T;
	static int N;
	static int[] X, Y;
	static double E;
	static List<Edge> edges;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; ++t) {
			
			// 정점 정보 입력
			N = Integer.parseInt(br.readLine());
			
			X = new int[N];
			Y = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; ++i) {
				X[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; ++i) {
				Y[i] = Integer.parseInt(st.nextToken());
			}
			
			E = Double.parseDouble(br.readLine());
			
			// 프림 알고리즘으로 간선을 선택
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			boolean[] visited = new boolean[N];
			
			pq.offer(new Edge(0, 0));
			int vertexCount = 0;
			double totalLenSquare = 0;
			
			while (!pq.isEmpty()) {
				Edge cur = pq.poll();
				
				if (visited[cur.target]) continue;
				
				visited[cur.target] = true;
				vertexCount++;
				totalLenSquare += cur.weight;
				
				if (vertexCount == N) break;
				
				for (int i = 0; i < N; ++i) {
					if (visited[i]) continue;
					
					pq.offer(new Edge(i, distanceSquare(cur.target, i)));
				}
			}
			
			System.out.printf("#%d %d\n", t, Math.round(totalLenSquare * E));
		}
	}
	
	public static double distanceSquare(int i, int j) {
		int xDiff = X[i] - X[j];
		int yDiff = Y[i] - Y[j];
		return Math.pow(xDiff, 2) + Math.pow(yDiff, 2);
	}
}