import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        int N, nextYear;
        for (int i = 0; i < T; ++i) {
            String line = bf.readLine();
            N = Integer.parseInt(line.substring(2));
            nextYear = Integer.parseInt(line) + 1;
            if (nextYear % N == 0) {
                System.out.println("Good");
            } else {
                System.out.println("Bye");
            }
        }
    }
}