import java.io.*;
import java.util.*;

public class Main {
	static int[][] paper = new int[10][10];
	static boolean[][] isCovered = new boolean[10][10];
	static int[] use = new int[6];  // use[a] : a * a 색종이 사용한 개수
	static int minUsedCount = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 10; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; ++j) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		search(0, 0);
		System.out.println(minUsedCount == Integer.MAX_VALUE ? -1 : minUsedCount);
	}
	
	public static void search(int idx, int usedCount) {
		if (usedCount > minUsedCount)
			return;
		
		if (idx == 100) {
			minUsedCount = usedCount;
			return;
		}
		
		int r = idx / 10;
		int c = idx % 10;
		
		if (paper[r][c] == 0 || isCovered[r][c]) {
			search(idx + 1, usedCount);
		} else {
			for (int size = 5; size >= 1; --size) {
				if (!canAttach(r, c, size)) continue;
				
				attach(r, c, size);
				search(idx + 1, usedCount + 1);
				detach(r, c, size);
			}
		}
	}
	
	public static boolean canAttach(int r, int c, int size) {
		if (r + size > 10 || c + size > 10) return false;
		if (use[size] >= 5) return false;
		
		for (int i = r; i < r + size; ++i)
			for (int j = c; j < c + size; ++j)
				if (isCovered[i][j] || paper[i][j] == 0) return false;
		
		return true;
	}
	
	public static void attach(int r, int c, int size) {
		for (int i = r; i < r + size; ++i)
			for (int j = c; j < c + size; ++j)
				isCovered[i][j] = true;
		use[size]++;
	}
	
	public static void detach(int r, int c, int size) {
		for (int i = r; i < r + size; ++i)
			for (int j = c; j < c + size; ++j)
				isCovered[i][j] = false;
		use[size]--;
	}
}