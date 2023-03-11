from collections import deque
def solution(priorities, location):
    answer = 0
    
    # priorities를 큐로써 다루기 위해 덱으로 변환
    dq = deque(priorities)
    
    # 존재하는 우선순위를 내림차순으로 정렬
    priorities.sort()
    priorities.reverse()
    p_desc = deque(priorities)
    
    cnt = 1
    while dq:
        # 대기 목록에서 출력된 문서가 가장 우선순위가 높은 문서라면
        if dq[0] == p_desc[0]:
            if location == 0:
                return cnt
            else:
                dq.popleft()
                p_desc.popleft()
                location -= 1
                cnt += 1
        else:
            if location == 0:
                location = len(dq) - 1
            else:
                location -= 1
            
            dq.append(dq.popleft())
    
    return answer