import sys
from collections import deque
input = sys.stdin.readline
move = [(1, 0), (0, 1), (-1, 0), (0, -1)]


# 현재의 빙산의 덩어리 수를 계산해 반환하고 1년 후의 상태로 빙산을 녹인다.
def count_and_melt(mat):
    count = 0
    check = [[False] * M for _ in range(N)]
    dq = deque()
    melt = []
    for i in range(N):
        for j in range(M):
            if not check[i][j] and mat[i][j] > 0:
                count += 1
                dq.append((i, j))
                while dq:
                    x, y = dq.popleft()
                    if check[x][y]:
                        continue
                    check[x][y] = True
                    sea = 0
                    for dx, dy in move:
                        nx, ny = x + dx, y + dy
                        if not (0 <= nx < N and 0 <= ny < M):
                            continue
                        if mat[nx][ny] == 0:
                            sea += 1
                            continue
                        if check[nx][ny]:
                            continue
                        dq.append((nx, ny))
                    melt.append((x, y, sea))
    for x, y, sea in melt:
        mat[x][y] = max(0, mat[x][y] - sea)
    return count


N, M = map(int, input().split())
iceberg = [list(map(int, input().split())) for _ in range(N)]
year = 0
while True:
    # for line in iceberg:
    #     for i in line:
    #         print(i, end=' ')
    #     print()
    # print('-----')
    sc = count_and_melt(iceberg)
    if sc == 0:
        print(0)
        break
    elif sc >= 2:
        print(year)
        break

    year += 1
