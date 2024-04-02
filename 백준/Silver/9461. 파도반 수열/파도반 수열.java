import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        
        long[] P = new long[101];
        P[1] = 1;
        P[2] = 1;
        P[3] = 1;
        for (int i = 4; i <= 100; ++i) {
            P[i] = P[i - 2] + P[i - 3];
        }
        
        for (int t = 1; t <= T; ++t) {
            System.out.println(P[Integer.parseInt(br.readLine())]);
        }
    }
}