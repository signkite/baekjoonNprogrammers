import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String str = (new BufferedReader(new InputStreamReader(System.in))).readLine();
        String rev = (new StringBuilder(str)).reverse().toString();
        System.out.println(str.equals(rev) ? 1 : 0);
    }
}