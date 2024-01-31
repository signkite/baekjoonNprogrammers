import java.io.*;

public class Main {
	static int N, K;
	static int[] mask;  // mask[x] : x 번째 단어의 사용 알파벳 집합을 비트마스크로 표현
	static int maxLearn;
	
	// 조합으로 선택된 알파벳 비트마스크로 표현 (a, c, i, n, t 는 원래부터 가지고 있는다)
	static int selectedAlpha = 0b10000010000100000101;
	static void combination(int i, int start) {
		if (i == K - 5) {
			int canLearn = 0;
			
			// 선택된 글자로만 배울 수 있는 단어 개수 세기 
			for (int j = 0; j < N; ++j) {
				if ((~selectedAlpha & mask[j]) == 0)
					canLearn++;
			}
			
			// 최대로 배울 수 있는 단어 갱신
			maxLearn = Math.max(maxLearn, canLearn);
			return;
		}
		
		for (int j = start; j < 26; ++j) {
			// a, c, i, n, t 는 미리 뽑았으므로 건너뛴다.
			if (j == 0 || j == 2 || j == 8 || j == 13 || j == 19) continue;
			
			selectedAlpha |= 1 << j;  // 뽑은 단어 삽입
			combination(i + 1, j + 1);
			selectedAlpha &= ~(1 << j);  // 뽑았던 단어 제거
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		K = Integer.parseInt(line[1]);
		
		// anta 와 tica를 구성하는 a, c, i, n, t 5개가 최소로 필요하다.
		if (K < 5) {
			System.out.println(0);
			return;
		}
		
		// 단어 입력받으며 포함하는 알파벳 비트마스크로 변환
		mask = new int[N];
		for (int i = 0; i < N; ++i) {
			String word = br.readLine();
			
			// a, c, i, n, t 미리 포함
			mask[i] = 0b10000010000100000101; 
			
			for (int j = 4, size = word.length(); j < size - 4; ++j) {
				mask[i] |= 1 << (word.charAt(j) - 'a');
			}
		}
		
		combination(0, 0);
		System.out.println(maxLearn);
	}
}
