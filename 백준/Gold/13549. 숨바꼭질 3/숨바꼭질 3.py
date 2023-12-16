from collections import deque

N, K = map(int, input().split())

# (현재 위치, 이동 쵯수)
dq = deque([(N, 0)])

# check[x] : x 번째 위치에 도달하는 경우를 체크했는지 여부
check = [False] * 200001
check[N] = True

while dq:
    X, move = dq.popleft()
    if X == K:
        print(move)
        break

    if X > 0 and not check[X - 1]:
        check[X - 1] = True
        dq.append((X - 1, move + 1))
    if X <= 100000 and not check[X * 2]:
        check[X * 2] = True
        dq.append((X * 2, move))
    if X < 200000 and not check[X + 1]:
        check[X + 1] = True
        dq.append((X + 1, move + 1))

