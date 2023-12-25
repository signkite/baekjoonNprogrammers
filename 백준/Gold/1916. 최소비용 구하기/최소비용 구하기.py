import sys
from collections import defaultdict
from heapq import heappop, heappush
input = sys.stdin.readline

N = int(input())
M = int(input())
graph = defaultdict(list)
distance = [float("inf")] * (N + 1)
visited = [False] * (N + 1)

for _ in range(M):
    s, e, cost = map(int, input().split())
    graph[s].append((e, cost))
start, end = map(int, input().split())

distance[start] = 0
hq = [(0, start)]
while hq:
    d, v = heappop(hq)

    if visited[v]:
        continue
    visited[v] = True

    for next_v, w in graph[v]:
        if distance[v] + w < distance[next_v]:
            distance[next_v] = d + w
            heappush(hq, (d + w, next_v))

print(distance[end])