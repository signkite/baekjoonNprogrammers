import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, M, K;
    static long[] data;  // 입력받은 데이터
    static long[] segTree;  // 세그먼트 트리의 데이터가 저장될 배열

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        data = new long[N];
        segTree = new long[4 * N];
        for (int i = 0; i < N; ++i) {
            data[i] = Long.parseLong(br.readLine());
        }

        initSegTree(0, N - 1, 0);

        for (int i = 0; i < M + K; ++i) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1)
                updateSegTree(0, N - 1, 0, b - 1, c - data[b - 1]);
            else
                sb.append(getSum(0, N - 1, 0, b - 1, (int) c - 1)).append("\n");
        }

        System.out.println(sb);
    }

    // 세그먼트 트리를 제작
    public static long initSegTree(int start, int end, int node) {

        // leaf node
        if (start == end) {
            segTree[node] = data[start];
        }

        // non-leaf node
        else {
            int mid = (start + end) >> 1;
            segTree[node] = initSegTree(start, mid, 2 * node + 1)
                            + initSegTree(mid + 1, end, 2 * node + 2);
        }

        return segTree[node];
    }

    // target 인덱스의 숫자 변화를 반영
    public static void updateSegTree(int start, int end, int node, int target, long diff) {

        // target 의 변화에 지장 없는 노드는 건너뛴다.
        if (target < start || end < target)
            return;

        // target 의 변화를 현재 노드에 반영
        segTree[node] += diff;

        // leaf 라면 그만 진행
        if (start == end) {
            data[start] += diff;
            return;
        }

        // 자식 노드에 내용 반영
        int mid = (start + end) >> 1;
        updateSegTree(start, mid, 2 * node + 1, target, diff);
        updateSegTree(mid + 1, end, 2 * node + 2, target, diff);
    }

    // targetStart 부터 targetEnd 까지의 구간합을 구한다.
    public static long getSum(int start, int end, int node, int targetStart, int targetEnd) {

        // 구간합을 구할 범위에 대해 책임이 없는 노드라면 건너뛴다.
        if (end < targetStart || targetEnd < start)
            return 0L;

        // 자신이 가진 범위가 모두 구간합을 구할 범위에 속한 노드라면
        if (targetStart <= start && end <= targetEnd)
            return segTree[node];

        // 위 두 경우가 모두 아니라면 자식 노드를 탐색
        int mid = (start + end) >> 1;
        return getSum(start, mid, 2 * node + 1, targetStart, targetEnd)
                + getSum(mid + 1, end, 2 * node + 2, targetStart, targetEnd);
    }
}