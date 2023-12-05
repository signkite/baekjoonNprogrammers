move = [[-1, 0], [1, 0], [0, 1], [0, -1]]

for _ in range(10):
    # 입력 받기
    test_case = int(input())
    maze = [list(input()) for _ in range(16)]
    check = [[False] * 16 for _ in range(16)]

    # 시작, 끝 좌표 확인
    start = []
    end = []
    for i in range(16):
        for j in range(16):
            if maze[i][j] == '2':
                start += [i, j]
            elif maze[i][j] == '3':
                end += [i, j]

    # depth first search 를 통해 도착지에 도달할 수 있는지 파악
    stack = [start]
    is_possible = 0
    while stack:
        x, y = stack.pop()

        # 도달할 수 있음이 파악되면 break
        if [x, y] == end:
            is_possible = 1
            break

        for dx, dy in move:
            nx, ny = x + dx, y + dy
            if nx < 0 or 16 <= nx:
                continue
            if ny < 0 or 16 <= ny:
                continue
            if check[nx][ny] or maze[nx][ny] == '1':
                continue
            stack.append([nx, ny])
            check[nx][ny] = True

    print(f"#{test_case} {is_possible}")