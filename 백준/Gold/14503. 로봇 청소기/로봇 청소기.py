#               M (y축)
# N (x축)  [ (r,c) ]
#
# 로봇 청소기가 있는 칸은 항상 빈칸

# d: 0- 위(북) 1- 오른(동) 2-아래(남) 3-왼(서)
# 반시계 90도 -> (d + 3) % 4
# 0 - 청소되지 않은 빈칸
# 1 - 벽
from collections import deque

N, M = map(int, input().split())
r, c, d = map(int, input().split())

# room
# 0- 청소 안된 빈칸
# 1- 벽
# 2- 청소 완료
room = [list(map(int, input().split())) for _ in range(N)]

dq = deque([(r, c, d)])
room[r][c] = 2
cnt = 1

# move[d] == 해당 방향으로 이동
move = [(-1, 0), (0, 1), (1, 0), (0, -1)]

while dq:
    x, y, d = dq.popleft()

    is_clean = True
    # 왼쪽부터 청소할 칸 있는지 탐색
    for i in range(1, 5):
        dx, dy = move[(d + 3 * i) % 4]
        nx, ny = x + dx, y + dy
        if 0 <= nx < N and 0 <= ny < M and room[nx][ny] == 0:
            dq.append((nx, ny, (d + 3 * i) % 4))
            cnt += 1
            room[nx][ny] = 2
            is_clean = False
            break

    # 청소할 칸 없다면
    if is_clean:
        dx, dy = move[(d + 6) % 4]
        nx, ny = x + dx, y + dy

        # 후진 가능
        if 0 <= nx < N and 0 <= ny < M and room[nx][ny] != 1:
            dq.append((nx, ny, d))
        # 후진 불가
        else:
            break

print(cnt)