from collections import deque

def solution(rows, columns, queries):
    answer = []
    
    mat = []
    num = 1
    for i in range(rows):
        line = []
        for j in range(num, num + columns):
            line.append(j)
        num += columns
        mat.append(line)
            
    for x1, y1, x2, y2 in queries:
        
        nums = deque()
        for y in range(y1 - 1, y2 - 1):
            nums.append(mat[x1 - 1][y])
        for x in range(x1 - 1, x2 - 1):
            nums.append(mat[x][y2 - 1])
        for y in range(y2 - 1, y1 - 1, -1):
            nums.append(mat[x2 - 1][y])
        for x in range(x2 - 1, x1 - 1, - 1):
            nums.append(mat[x][y1 - 1])
        
        answer.append(min(nums))
        
        for y in range(y1, y2):
            mat[x1 - 1][y] = nums.popleft()
        for x in range(x1, x2):
            mat[x][y2 - 1] = nums.popleft()
        for y in range(y2 - 2, y1 - 2, -1):
            mat[x2 - 1][y] = nums.popleft()
        for x in range(x2 - 2, x1 - 2, -1):
            mat[x][y1 - 1] = nums.popleft()
        
    return answer