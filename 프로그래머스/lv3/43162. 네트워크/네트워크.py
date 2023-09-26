from collections import deque

def solution(n, computers):
    answer = 0
    check = [False] * n
    # 1번부터 연결된 애들 check
    # bfs로 체크?
    # False인 친구 발견 => 카운트 업
    # 해당 친구로부터 연결된 애들 모두 True로 처리
    
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