import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        boolean[] nums = new boolean[31];

        for(int i = 0; i < 28; ++i) {
            nums[Integer.parseInt(bf.readLine())] = true;
        }

        for(int i = 1; i < 31; ++i) {
            if (!nums[i]) {
                System.out.println(i);
            }
        }
    }
}