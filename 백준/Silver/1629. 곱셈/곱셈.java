import java.io.*;
import java.util.*;

public class Main {
    public static int A, B, C;
    // A를 n번 곱한 수를 C로 나눈 나머지를 구하는 함수
    public static long mod(int n) {
        long mod1 = (long)(A % C);

        if (n == 1) {
            return mod1;
        }

        long tmp = mod(n / 2);

        if (n % 2 == 0) {
            return (tmp * tmp) % C;
        } else {
            return ((tmp * tmp % C) * mod1) % C;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");

        A = Integer.parseInt(line[0]);
        B = Integer.parseInt(line[1]);
        C = Integer.parseInt(line[2]);

        System.out.println(mod(B));
    }
}