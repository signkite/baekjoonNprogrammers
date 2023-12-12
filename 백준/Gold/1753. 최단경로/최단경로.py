from heapq import heappop, heappush
import sys

input = sys.stdin.readline

V, E = map(int, input().split())
K = int(input())

graph = [[] for _ in range(V + 1)]
for _ in range(E):
    u, v, w = map(int, input().split())
    graph[u].append((v, w))

# val[i]: K번째 정점부터 i번째 정점에 도달하는 최단 경로값.
# 200000 인 경우 INF로 취급
val = [200000] * (V + 1)
val[K] = 0

q = [(0, K)]
while q:
    cur_w, cur_v = heappop(q)
    if cur_w > val[cur_v]:
        continue

    for v, w in graph[cur_v]:
        total_w = cur_w + w
        if total_w < val[v]:
            val[v] = total_w
            heappush(q, (total_w, v))

for i in range(1, V + 1):
    if val[i] == 200000:
        print('INF')
    else:
        print(val[i])
