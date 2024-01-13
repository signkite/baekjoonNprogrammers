import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Deque<Integer> stack = new ArrayDeque<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] target = new int[n];

        for (int i = 0; i < n; ++i) {
            target[i] = Integer.parseInt(br.readLine());
        }

        int curNum = 1;
        int curTargetIdx = 0;
        List<Character> operation = new ArrayList<>();
        boolean isPossible = true;
        while (curNum <= n) {
            // target의 현재 인덱스 수가 stack의 peek에 올라올 때까지 집어넣는다.
            while (curNum <= target[curTargetIdx]) {
                stack.push(curNum++);
                operation.add('+');
            }

            // stack에 담긴 수들이 target과 동일한 구간을 모두 비교
            while (!stack.isEmpty() && stack.peek() == target[curTargetIdx]) {
                stack.pop();
                operation.add('-');
                curTargetIdx++;
            }

            if (curTargetIdx == n) break;
            if (curNum > target[curTargetIdx]) {
                isPossible = false;
                break;
            }
        }

        if (!stack.isEmpty()) isPossible = false;
        if (isPossible) {
            for (char c: operation) {
                System.out.println(c);
            }
        } else {
            System.out.println("NO");
        }
    }
}