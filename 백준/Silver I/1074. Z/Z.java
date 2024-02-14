import java.io.*;
import java.util.*;

public class Main {
	static int N, targetR, targetC;
	static int count;
	
	public static void check(int r, int c) {
		if (r == targetR && c == targetC) {
			System.out.println(count);
			System.exit(0);
		}
	}
	
	public static void visit(int size, int r, int c) {		
		if (size <= 1) {
			check(r, c); count++;
			check(r, c + 1); count++;
			check(r + 1, c); count++;
			check(r + 1, c + 1); count++;
			return;
		}
		
		size--;
		
		if (targetR < r + (1 << size)) {
			
			if (targetC < c + (1 << size)) {
				visit(size, r, c);				
			} else {
				count += 1 << (size * 2);
				visit(size, r, c + (1 << size));				
			}
			
		} else {
			
			if (targetC < c + (1 << size)) {
				count += (1 << (size * 2)) * 2;
				visit(size, r + (1 << size), c);			
			} else {
				count += (1 << (size * 2)) * 3;
				visit(size, r + (1 << size), c + (1 << size));			
			}
			
		} 
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		targetR = Integer.parseInt(st.nextToken());
		targetC = Integer.parseInt(st.nextToken());
		
		visit(N, 0, 0);
	}
}