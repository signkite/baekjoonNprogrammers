import java.io.*;
import java.util.*;

public class Solution {
	
	static class Node {
		int v;
		Node next;
		
		Node (int v, Node next) {
			this.v = v;
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 1; t <= 10; ++t) {
			Node[] numbers = new Node[101];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int len = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < len / 2; ++i) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				numbers[from] = new Node(to, numbers[from]);
			}
			
			Queue<Integer> queue = new ArrayDeque<>();
			queue.offer(start);
			int maxNum = start;
			boolean[] visit = new boolean[101];
			visit[start] = true;

			while (true) {
				
				// queue의 size만큼 (현재 depth의 노드 수만큼) 연락 수행
				int qSize = queue.size();
				
				for (int i = 0; i < qSize; ++i) {
					int cur = queue.poll();
					maxNum = Math.max(cur, maxNum);
					
					for (Node n = numbers[cur]; n != null; n = n.next) {
						if (visit[n.v]) continue;
						
						visit[n.v] = true;
						queue.offer(n.v);
					}
				}
				
				if (queue.isEmpty()) break;
				maxNum = 0;
			}
			
			System.out.printf("#%d %d\n", t, maxNum);
		}
	}
}