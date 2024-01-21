import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 숫자를 lowerGroup, upperGroup 두 그룹으로 나눈다.
        // lowerGroup은 최대 힙, upperGroup은 최소 힙으로 저장한다.
        // 숫자가 들어올 때마다 lowerGroup의 최대값, upperGroup의 최소값과 비교하여 어느 그룹이 넣을지 결정한다.
        // 그 과정에서 [upperGroup size] <= [lowerGroup size] <= [upperGroup size + 1] 을 유지한다.
        PriorityQueue<Integer> lowerGroup = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> upperGroup = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; ++i) {
            int inputNum = Integer.parseInt(br.readLine());

            // 현재까지 말한 수의 개수가 짝수: upperGroup 에 수를 추가, 홀수면 lowerGroup에 추가
            if (i % 2 == 0) upperGroup.offer(inputNum);
            else lowerGroup.offer(inputNum);

            // 새로 넣은 수로 인해 lowerGroup의 최대값이 upperGroup의 최소값보다 큰 경우 두 값을 치환
            if (!upperGroup.isEmpty() && upperGroup.peek() < lowerGroup.peek()) {
                upperGroup.offer(lowerGroup.poll());
                lowerGroup.offer(upperGroup.poll());
            }
            sb.append(lowerGroup.peek() + "\n");
        }

        System.out.println(sb);
    }
}