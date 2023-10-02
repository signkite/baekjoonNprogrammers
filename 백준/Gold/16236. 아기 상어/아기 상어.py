from collections import deque

N = int(input())
space = [list(map(int, input().split())) for _ in range(N)]

move = [[-1, 0], [0, -1], [0, 1], [1, 0]]


# 아기상어 위치 인식
def get_start_pos():
    global space
    for i in range(N):
        for j in range(N):
            if space[i][j] == 9:
                space[i][j] = 0
                return [i, j]


# stt position 부터 dst position 까지 최단거리 측정
# 먹을 수 있는 친구 위치, 그 곳까지의 시간 반환
def get_time(stt, size):
    visited = [[False] * N for _ in range(N)]
    visited[stt[0]][stt[1]] = True

    dq = deque([stt + [0]])
    flag = False
    min_count = 0
    target = [20, 20]
    while dq:
        x, y, count = dq.popleft()

        if flag and count >= min_count:
            if not dq:
                return min_count, target
            elif 0 < space[x][y] < size:
                if target[0] > x:
                    target = [x, y]
                elif target[0] == x and target[1] > y:
                    target = [x, y]
            continue

        for dx, dy in move:
            nx, ny = x + dx, y + dy

            if 0 <= nx < N and 0 <= ny < N and not visited[nx][ny] and space[nx][ny] <= size:
                if 0 < space[nx][ny] < size:
                    flag = True
                    min_count = count + 1
                    if target == [20, 20]:
                        target = [nx, ny]
                visited[nx][ny] = True
                dq.append([nx, ny, count + 1])

    # dst에 갈 수 없다면 -1 반환
    return -1, []


cur_pos = get_start_pos()
total_time = 0
shark_size = 2
eat_count = 0
while True:
    time, dst = get_time(cur_pos, shark_size)
    if time < 0:
        break

    total_time += time
    x, y = dst

    space[x][y] = 0
    eat_count += 1
    if eat_count == shark_size:
        eat_count = 0
        shark_size += 1

    cur_pos = dst

print(total_time)