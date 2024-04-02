import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int A = sc.nextInt();
		int B = sc.nextInt();
	
		int a = A;
		int b = B;
		
		// 더 큰 수가 a가 되도록 한다.
		if (a < b) {
			int temp = a;
			a = b;
			b = temp;
		}
		
		// 유클리드 호제법 수행
		while (a % b != 0) {
			int temp = a % b;
			
			a = b;
			b = temp;
		}
		
		int GCD = b;
		int LCM = A * B / GCD;
		
		System.out.println(GCD);
		System.out.println(LCM);
	}
}