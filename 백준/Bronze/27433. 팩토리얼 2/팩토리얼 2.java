import java.util.*;

class Main {
    public static void main(String[] args) {
        int N = (new Scanner(System.in)).nextInt();
        
        long answer = 1;
        for (long i = 1; i <= N; ++i)
            answer *= i;
        
        System.out.println(answer);
    }
}