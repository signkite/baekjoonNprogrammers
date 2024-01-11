import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            char[] stack = new char[100];
            int top = -1;

            String str = br.readLine();
            if (str.equals(".")) break;

            boolean isPossible = true;
            int idx = 0;
            char c = str.charAt(0);
            while(c != '.') {
                if (c == '[' || c == '(') {
                    stack[++top] = c;
                } else if (c == ']') {
                    if (top == -1 || stack[top] != '[') {
                        isPossible = false;
                        break;
                    }
                    top--;
                } else if (c == ')') {
                    if (top == -1 || stack[top] != '(') {
                        isPossible = false;
                        break;
                    }
                    top--;
                }
                c = str.charAt(++idx);
            }

            System.out.println(top == -1 && isPossible ? "yes" : "no");
        }
    }
}