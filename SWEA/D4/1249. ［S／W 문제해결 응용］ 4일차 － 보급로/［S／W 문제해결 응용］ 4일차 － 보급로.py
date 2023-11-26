from collections import deque
T = int(input())

move = [(-1, 0), (0, -1), (1, 0), (0, 1)]
for test_case in range(1, T + 1):
    N = int(input())
    mat = [list(map(int, input())) for _ in range(N)]
    time = [[-1] * N for _ in range(N)]
    time[0][0] = 0
    check = [[False] * N for _ in range(N)]

    dq = deque()
    dq.append((0, 0))

    while dq:
        x, y = dq.popleft()
        check[x][y] = True

        for dx, dy in move:
            nx, ny = x + dx, y + dy
            if 0 <= nx < N and 0 <= ny < N:
                cur_time = time[x][y] + mat[nx][ny]
                if time[nx][ny] < 0 or cur_time < time[nx][ny]:
                    dq.append((nx, ny))
                    time[nx][ny] = cur_time

    print(f"#{test_case} {time[N - 1][N - 1]}")