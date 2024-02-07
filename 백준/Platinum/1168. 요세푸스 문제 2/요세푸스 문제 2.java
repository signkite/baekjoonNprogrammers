import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] segTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int position = K;

        segTree = new int[4 * N];
        initSegTree(0, N - 1, 0);

        sb.append("<");
        for (int i = 0; i < N; ++i) {
            int removedNum = getTargetNum(0, N - 1, 0, position);
            sb.append(removedNum);
            removeTarget(0, N - 1, 0, removedNum - 1);

            if (i != N - 1) {
                sb.append(", ");
                position = (position + (K - 1) - 1) % segTree[0] + 1;
            }
        }
        sb.append(">");

        System.out.println(sb.toString());
    }

    // 사람 수를 구간합으로 나타내는 세그먼트 트리를 작성
    static int initSegTree(int start, int end, int node) {

        // 리프노드는 1 (사람 1명 이라는 의미)
        if (start == end)
            segTree[node] = 1;

        else {
            int mid = (start + end) / 2;
            segTree[node] = initSegTree(start, mid, 2 * node + 1)
                            + initSegTree(mid + 1, end, 2 * node + 2);
        }

        return segTree[node];
    }

    // target에 해당하는 사람이 제거되었을 때 구간합을 갱신
    static void removeTarget(int start, int end, int node, int target) {

        // target에 대해 책임이 없는 노드라면 건너 뛴다.
        if (target < start || end < target)
            return;

        // 노드 값 갱신
        segTree[node] --;

        // non-leaf 라면 자식에도 내용 반영
        if (start != end) {
            int mid = (start + end) / 2;
            removeTarget(start, mid, 2 * node + 1, target);
            removeTarget(mid + 1, end, 2 * node + 2, target);
        }
    }

    // order에 해당하는 사람이 몇 번인지 구한다.
    static int getTargetNum(int start, int end, int node, int order) {

        // leaf
        if (start == end)
            return start + 1;

        int mid = (start + end) / 2;

        // 앞쪽에 존재하는 사람 수가 order 이상이라면 왼쪽에 해당
        if (order <= segTree[2 * node + 1])
            return getTargetNum(start, mid, 2 * node + 1, order);

        // 본인의 앞에 자신의 순서보다 적은 수의 사람이 있다면, 뒷 그룹의 order - (앞 그룹 사람수) 번째
        else
            return getTargetNum(mid + 1, end, 2 * node + 2, order - segTree[2 * node + 1]);
    }
}
