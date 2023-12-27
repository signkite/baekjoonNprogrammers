from collections import defaultdict, deque
import sys
input = sys.stdin.readline

# 첫 번째 시도: 목적하는 건물에 필요한 건물 목록을 루트노드까지 탐색하여 시간을 구함
# -> 시간 초과 발생
# 두 번째 시도: 첫 번째 시도한 방식에 dp 적용
# -> 시간 초과 발생
# 세 번째 시도: 위상 정렬 사용

T = int(input())
for _ in range(T):
    N, K = map(int, input().split())

    # D[x] : x번 건물의 건설 시간
    # dep[x] : x번 건물을 짓기 위해 지어야하는 건물의 수
    # ready_time[x] : x번 건물을 짓기 위한 의존성이 있는 건물을 모두 완공하는데 걸리는 시간
    D = [0] + list(map(int, input().split()))
    dep = [0] * (N + 1)
    ready_time = [0] * (N + 1)

    # graph[x] : x번 건물을 필요로 하는 건물 번호 리스트
    graph = defaultdict(list)
    for _ in range(K):
        x, y = map(int, input().split())
        graph[x].append(y)
        dep[y] += 1

    # 의존성이 0인 건물들부터 모두 짓는다
    dq = deque()
    for i in range(1, N + 1):
        if dep[i] == 0:
            dq.append(i)

    dst = int(input())
    while dq:
        x = dq.popleft()
        if x == dst:
            break

        build_time = ready_time[x] + D[x]
        for y in graph[x]:
            if ready_time[y] < build_time:
                ready_time[y] = build_time
            dep[y] -= 1

            # 의존성이 모두 체크된 건물을 dq에 넣는다.
            if dep[y] == 0:
                dq.append(y)

    print(D[dst] + ready_time[dst])