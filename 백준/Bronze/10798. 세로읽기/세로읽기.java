import java.io.*;
import java.util.*;

class Main {
	static String[] words = new String[5];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 5; ++i) words[i] = br.readLine();
		
		int idx = 0;
		boolean isFinish;
		while (true) {
			isFinish = true;
			
			for (int i = 0; i < 5; ++i) {
				if (words[i].length() <= idx) continue;
				
				System.out.print(words[i].charAt(idx));
				isFinish = false;
			}
			
			idx++;
			if (isFinish) break;
		}
	}
}