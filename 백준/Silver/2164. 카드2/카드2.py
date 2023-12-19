from collections import deque

N = int(input())
dq = deque(list(range(1, N + 1)))

cnt = 0
while len(dq) > 1:
    if cnt % 2 == 0:
        dq.popleft()
    else:
        tmp = dq.popleft()
        dq.append(tmp)
    cnt += 1
print(dq.pop())