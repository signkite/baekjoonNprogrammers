from collections import deque

def solution(n, computers):
    answer = 0
    check = [False] * n
    
    dq = deque()
    for com_num in range(n):
        if check[com_num]:
            continue
            
        answer += 1
        dq.append(com_num)
        while dq:
            cur = dq.popleft()
            for i, connect in enumerate(computers[cur]):
                if connect and not check[i]:
                    check[i] = True
                    dq.append(i)
    
    return answer