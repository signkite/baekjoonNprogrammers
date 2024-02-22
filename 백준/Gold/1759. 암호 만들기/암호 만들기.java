import java.io.*;
import java.util.*;

public class Main {
	static int L, C;
	static char[] cArr;
	static char[] password;
	static boolean[] isVowel;
	
	// 사전순으로 정렬된 cArr에서 dfs로 가능성 있는 암호를 탐색
	public static void findPassword(int idx, int start, int vowel) {
		if (idx == L) {
			// 모음이 한 개 이상 있어야한다. 
			if (0 < vowel)
				System.out.println(new String(password));
			return;
		}
		
		for (int i = start; i < C; ++i) {
			// 자음이 2개 이상 있어야한다.
			if (isVowel[i])
				if (vowel == L - 2) continue;
				else vowel++;

			password[idx] = cArr[i];
			findPassword(idx + 1, i + 1, vowel);
			if (isVowel[i]) vowel--;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		password = new char[L];
		cArr = new char[C];
		isVowel = new boolean[C];
		
		String line = br.readLine();
		for (int i = 0; i < C; ++i) {
			cArr[i] = line.charAt(2 * i);
		}
		
		// 입력받은 문자를 정렬 후 모음인 곳의 위치 체크
		Arrays.sort(cArr);
		for (int i = 0; i < C; ++i) {
			char c = cArr[i];
			if (c == 'a' || c == 'i' || c == 'o' || c == 'u' || c == 'e')
				isVowel[i] = true;
		}
		
		findPassword(0, 0, 0);
	}
}