import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; ++t) {
			int N = Integer.parseInt(br.readLine());
			int odd = 0, even = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int maxHeight = 0;
			int[] trees = new int[N];
			for (int i = 0; i < N; ++i) {
				trees[i] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, trees[i]);
			}
			
			int answer = 0;
			for (int i = 0; i < N; ++i) {
				int diff = maxHeight - trees[i];
				
				if (diff == 0) continue;
				
				even += diff / 2;
				odd += diff % 2;
			}
			
			while (even > 0 && odd > 0) {
				even--; odd--; answer += 2;
			}
			
			if (even > 0) {
				answer += (even - 1) / 3 + even + 1;
			}
			
			if (odd > 0) {
				answer += 2 * odd - 1;
			}
			
			System.out.printf("#%d %d\n", t, answer);
		}
	}
}