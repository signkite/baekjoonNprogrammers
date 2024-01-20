import java.io.*;
import java.util.*;

class Lecture implements Comparable<Lecture>{
    public int S;
    public int T;

    public Lecture (int start, int terminate) {
        S = start;
        T = terminate;
    }

    // 힙 내에서 시작시간이 빠른 순으로, 시작시간이 같다면 종료 시간이 빠른 순으로 정렬
    @Override
    public int compareTo(Lecture lec) {
        if (this.S == lec.S) {
            return this.T - lec.T;
        } else {
            return this.S - lec.S;
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        PriorityQueue<Lecture> lecturePQ = new PriorityQueue<>();
        PriorityQueue<Integer> roomPQ = new PriorityQueue<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int S, T, i;
        String[] line;

        for (i = 0; i < N; ++i) {
            line = br.readLine().split(" ");
            S = Integer.parseInt(line[0]);
            T = Integer.parseInt(line[1]);
            lecturePQ.offer(new Lecture(S, T));
        }

        Lecture lecture;
        while (!lecturePQ.isEmpty()) {
            lecture = lecturePQ.poll();

            if (roomPQ.isEmpty())
                roomPQ.offer(lecture.T);
            else if (roomPQ.peek() <= lecture.S) {
                roomPQ.poll();
                roomPQ.offer(lecture.T);
            } else
                roomPQ.offer(lecture.T);
        }

        System.out.println(roomPQ.size());
    }
}