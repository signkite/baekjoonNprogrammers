import java.io.*;
import java.util.*;

public class Main {
	static int[] nums = new int[9];  // 난쟁이 모자에 적힌 수들
	static int[] data = new int[7];  // 모자의 수 7개 조합
	static boolean found;
	
	public static void combination(int i, int start) {
		if (found) return;
		
        // 7개 조합을 모두 찾으면 합계가 100 되는지 검사
		if (i == 7) {
			int sum = Arrays.stream(data).sum();
			if (sum == 100) {
				for (int j = 0; j < 7; ++j) {
					System.out.println(data[j]);
				}
				found = true;
			}
			return;
		}
		
		for (int j = start; j < 9; ++j) {
			data[i] = nums[j];
			combination(i + 1, j + 1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 9; ++i) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		combination(0, 0);
	}
}
