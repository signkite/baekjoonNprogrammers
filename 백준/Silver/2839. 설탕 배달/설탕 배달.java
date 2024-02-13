import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N = (new Scanner(System.in)).nextInt();

        // 최대한 많은 봉지를 5 킬로 봉지로 채운다
        int weight5 = N / 5;

        // 남은 무게가 3의 배수가 될 때까지 5 킬로 봉지 개수를 줄인다.
        while ((N - 5 * (weight5)) % 3 != 0 && weight5 >= 0) {
            weight5--;
        }

        if (weight5 == -1)
            System.out.println(-1);
        else
            System.out.println(weight5 + (N - 5 * weight5) / 3);
    }
}
