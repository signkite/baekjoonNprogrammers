from collections import deque

T = int(input())

move = [(0, -1), (-1, 0), (1, 0), (0, 1)]


def bfs(start):
    x, y = start
    dq = deque([[x, y, 1, board[x][y]]])

    while dq:
        x, y, length, cur_num = dq.popleft()

        for dx, dy in move:
            nx, ny = x + dx, y + dy
            if 0 <= nx < 4 and 0 <= ny < 4:
                new_num = cur_num + board[nx][ny]
                if length == 6:
                    nums.append(new_num)
                else:
                    dq.append([nx, ny, length + 1, new_num])


for test_case in range(1, T + 1):
    board = [input().split() for _ in range(4)]
    nums = []

    for i in range(4):
        for j in range(4):
            bfs([i, j])

    print(f"#{test_case} {len(set(nums))}")