import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str;
        for(int i = 0; i < n; ++i) {
            str = br.readLine();
            System.out.println(str.charAt(0) + "" + str.charAt(str.length() - 1));
        }
    }
}