from collections import deque
def solution(maps): 
    rows = len(maps)
    cols = len(maps[0])
    
    # bfs로 탐색, 가장 먼저 도달한 루트가 가장 빠른 루트
    # [x, y, move_count]
    dq = deque([(0, 0, 1)])
    dx = (0, 0, 1, -1)
    dy = (1, -1, 0, 0)
    
    while dq:
        cur_x, cur_y, move_count = dq.popleft()
        
        for i in range(4):
            new_x = cur_x + dx[i]
            new_y = cur_y + dy[i]
            
            if 0 <= new_x < cols and 0 <= new_y < rows and maps[new_y][new_x] == 1:       
                # 목적지 도달시 정답 반환
                if new_x == cols - 1 and new_y == rows - 1:
                    return move_count + 1
                maps[new_y][new_x] = 0
                dq.append((new_x, new_y, move_count + 1))

    return -1