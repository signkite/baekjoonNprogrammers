import java.io.*;

class Main {
    public static void main(String[] agrs) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String fileName = br.readLine();
        char[] chrs = fileName.toCharArray();

        for (int i = 0; i < N - 1; ++i) {
            fileName = br.readLine();

            for (int j = 0; j < chrs.length; ++j) {
                if (fileName.charAt(j) != chrs[j]) {
                    chrs[j] = '?';
                }
            }
        }

        System.out.println(String.valueOf(chrs));
    }
}