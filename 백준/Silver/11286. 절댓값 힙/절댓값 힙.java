import java.io.*;
import java.util.*;
class AbsNum implements Comparable<AbsNum> {
    public boolean isPositive;
    public int abs;
    public AbsNum(int num) {
        if (num >= 0) {
            this.isPositive = true;
            this.abs = num;
        } else {
            this.isPositive = false;
            this.abs = -num;
        }
    }
    public int getOriginalNum() {
        if (isPositive) return abs;
        else return -abs;
    }

    @Override
    public int compareTo (AbsNum a) {
        if (this.abs == a.abs) {
            if (this.isPositive) {
                return a.isPositive ? 0 : 1;
            } else {
                return a.isPositive ? -1 : 0;
            }
        }
        return this.abs - a.abs;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        PriorityQueue<AbsNum> pq = new PriorityQueue<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int inputNum;
        for (int i = 0; i < N; ++i) {
            inputNum = Integer.parseInt(br.readLine());
            if (inputNum == 0) {
                if (pq.isEmpty()) {
                    System.out.println(0);
                } else {
                    System.out.println(pq.poll().getOriginalNum());
                }
            } else {
                pq.offer(new AbsNum(inputNum));
            }
        }
    }
}