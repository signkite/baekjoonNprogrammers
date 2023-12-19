from collections import deque

# 가로, 세로, 높이
M, N, H = map(int, input().split())

box = []
for _ in range(H):
    box.append([list(map(int, input().split())) for _ in range(N)])

# (높이, 세로, 가로, 걸린 일 수) 를 dq에 저장하여 bfs
dq = deque()

# 익은 토마토의 좌표 찾기 & 모두 익어있는지 확인
not_ripen = 0
for i in range(H):
    for j in range(N):
        for k in range(M):
            if box[i][j][k] == 1:
                dq.append((i, j, k, 0))
            elif box[i][j][k] == 0:
                not_ripen += 1

# 모두 익어있다면
if not_ripen == 0:
    print(0)
    
else:
    move = [(-1, 0, 0), (1, 0, 0), (0, 1, 0), (0, -1, 0), (0, 0, 1), (0, 0, -1)]
    while dq:
        x, y, z, day = dq.popleft()
        for dx, dy, dz in move:
            nx, ny, nz = x + dx, y + dy, z + dz
            
            # 상자의 범위를 벗어나는 경우 제외
            if nx < 0 or H <= nx:
                continue
            if ny < 0 or N <= ny:
                continue
            if nz < 0 or M <= nz:
                continue
                
            # 살피려는 칸이 비어있는 칸이거나 이미 익어있는 토마토가 있는 경우 제외 
            if box[nx][ny][nz] != 0:
                continue
                
            # 안익은 토마토 발견시 며칠 걸려 익었는지를 표시 
            box[nx][ny][nz] = day + 1
            dq.append((nx, ny, nz, day + 1))
    
    # 익은 날짜 표시를 살펴 최대 일수를 확인
    max_day = 0
    for board in box:
        for line in board:
            for tomato in line:
                # 안익은 토마토가 존재한다면 익을 수 없는 경우이다.
                if tomato == 0:
                    print(-1)
                    exit(0)
                elif tomato > max_day:
                    max_day = tomato
    print(max_day)
