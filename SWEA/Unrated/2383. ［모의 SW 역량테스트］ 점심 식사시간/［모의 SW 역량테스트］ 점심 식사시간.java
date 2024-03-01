import java.io.*;
import java.util.*;

public class Solution {
    static int T;
    static int N;

    static int fir;				// 첫 번째, 두 번째 계단의 길이
    static int sec;

    static int[] firPoint; 		// 계단의 좌표
    static int[] secPoint;

    static int[] toFir;			// i번째 사람의 계단까지의 거리
    static int[] toSec;

    static boolean[] isFir;		// 사람들의 계단을 분배 (true인 경우 해당 index 사람은 첫 번째 계단 이용)

    static List<int[]> points;  // 사람들의 현재 위치
    static int numOfHumans; 	// 사람 수

    static int minTime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; ++t) {
            N = Integer.parseInt(br.readLine());

            fir = 0;
            points = new ArrayList<>();

            // 사람들의 좌표, 계단 좌표, 계단 길이 입력
            for (int i = 0; i < N; ++i) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for(int j = 0; j < N; ++j) {
                    int num = Integer.parseInt(st.nextToken());

                    if (num == 1)
                        points.add(new int[] {i, j});

                    else if (num >= 2) {
                        if (fir == 0) {
                            fir = num;
                            firPoint = new int[] {i, j};
                        }
                        else {
                            sec = num;
                            secPoint = new int[] {i, j};
                        }
                    }
                }
            }

            numOfHumans = points.size();
            toFir = new int[numOfHumans];
            toSec = new int[numOfHumans];
            isFir = new boolean[numOfHumans];

            minTime = Integer.MAX_VALUE;

            calcDist();
            distribute(0);

            System.out.printf("#%d %d\n", t, minTime);
        }
    }

    // 각 사람들의 첫번째, 두번째 계단까지의 거리 계산
    public static void calcDist() {
        int idx = 0;

        for (int[] point: points) {
            toFir[idx] = Math.abs(point[0] - firPoint[0]) +  Math.abs(point[1] - firPoint[1]);
            toSec[idx] = Math.abs(point[0] - secPoint[0]) +  Math.abs(point[1] - secPoint[1]);

            idx++;
        }
    }

    // 각 사람별로 첫번째 혹은 두 번째 계단 분배
    public static void distribute(int idx) {
        if (idx == numOfHumans) {
            minTime = Math.min(solve(), minTime);
            return;
        }

        isFir[idx] = true;
        distribute(idx + 1);
        isFir[idx] = false;
        distribute(idx + 1);
    }

    // 주어진 isFir를 가지고 모든 사람들이 이동을 완료하는 시간을 반환
    public static int solve() {
        List<Integer> f = new ArrayList<>();
        List<Integer> s = new ArrayList<>();

        for (int i = 0; i < numOfHumans; ++i) {
            if (isFir[i]) f.add(toFir[i]);
            else s.add(toSec[i]);
        }

        Collections.sort(f);
        Collections.sort(s);

        int fTime = calcTime(f, fir);
        int sTime = calcTime(s, sec);

        return Math.max(fTime, sTime);
    }

    public static int calcTime(List<Integer> arrive, int stairLen) {
        if (arrive.size() == 0)
            return 0;

        // 도착한 사람 순으로 계단을 완전히 내려가는 시간을 넣을 큐
        Queue<Integer> stair = new ArrayDeque<>();

        int idx = 0;
        while (idx < arrive.size()) {
            int cur = arrive.get(idx);

            // 계단 큐에 3명이 있는 경우
            if (stair.size() == 3) {
                int usainBolt = stair.poll();

                // 가장 먼저 계단에 도착했던 사람이 다 내려간 뒤 도착했다면
                if (usainBolt <= arrive.get(idx))
                    stair.offer(cur + stairLen + 1);

                // 대기한 경우
                else stair.offer(usainBolt + stairLen);

            } else {
                stair.offer(cur + stairLen + 1);
            }

            idx++;
        }

        int time = 0;
        while (!stair.isEmpty())
            time = Math.max(stair.poll(), time);

        return time;
    }
}