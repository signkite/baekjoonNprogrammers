import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        
        String N = line[0];
        int B = Integer.parseInt(line[1]);
        int answer = 0, cnt = 1;
        
        for (int i = N.length() - 1; i >= 0; --i) {
            char c = N.charAt(i);
            if (c >= 'A') {
                answer += cnt * (10 + c - 'A');
            } else {
                answer += cnt * (c - '0');
            }
            cnt *= B;
        }
        
        System.out.println(answer);
    }
}