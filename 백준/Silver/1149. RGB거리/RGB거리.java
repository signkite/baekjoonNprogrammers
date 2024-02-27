import java.io.*;
import java.util.*;

class Main {
    static int N;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        int Rmin = 0, prevRmin = 0;
        int Gmin = 0, prevGmin = 0;
        int Bmin = 0, prevBmin = 0;
        
        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int R = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            
            Rmin = Math.min(R + prevGmin, R + prevBmin);
            Gmin = Math.min(G + prevRmin, G + prevBmin);
            Bmin = Math.min(B + prevRmin, B + prevGmin);
            
            prevRmin = Rmin;
            prevGmin = Gmin;
            prevBmin = Bmin;
        }
        
        int answer = Math.min(Rmin, Gmin);
        answer = Math.min(Bmin, answer);
        System.out.println(answer);
    }
}