import java.io.*;
import java.util.*;

public class Solution {
	static int T;
	static int V, E;
	static int A, B, C;
	static int[] parents;
	static Edge[] edges;
	
	static class Edge implements Comparable<Edge> {
		int v1, v2, value;
		
		Edge (int v1, int v2, int value) {
			this.v1 = v1;
			this.v2 = v2;
			this.value = value;
		}
		
		@Override
		public int compareTo(Edge anotherEdge) {
			return this.value - anotherEdge.value;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			edges = new Edge[E];
			parents = new int[V + 1];
			for (int i = 1; i <= V; ++i) {
				parents[i] = i;
			}
			
			for (int i = 0; i < E; ++i) {
				st = new StringTokenizer(br.readLine());
				
				A = Integer.parseInt(st.nextToken());
				B = Integer.parseInt(st.nextToken());
				C = Integer.parseInt(st.nextToken());
				
				edges[i] = new Edge(A, B, C);
			}
			
			Arrays.sort(edges);
			
			union(edges[0].v1, edges[0].v2);
			int startNode = edges[0].v1;
			long answer = edges[0].value;
			int curEdges = 1;
			for (int i = 1; i < E; ++i) {
				if (curEdges == V - 1)
					break;
				
				if (find(edges[i].v1) == find(edges[i].v2))
					continue;
				
				union(edges[i].v1, edges[i].v2);
				answer += edges[i].value;
				curEdges++;
			}
			
			System.out.printf("#%d %d\n", t, answer);
		}
		
	}
	
	public static int find(int x) {
		if (x == parents[x])
			return x;
		
		return parents[x] = find(parents[x]);
	}
	
	public static void union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);
		
		if (rootX != rootY)
			parents[rootX] = rootY;
	}
}