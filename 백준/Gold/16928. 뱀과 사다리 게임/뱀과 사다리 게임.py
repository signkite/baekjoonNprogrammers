from collections import defaultdict, deque

N, M = map(int, input().split())

ladder = defaultdict(int)
snake = defaultdict(int)
for _ in range(N):
    x, y = map(int, input().split())
    ladder[x] = y
for _ in range(M):
    u, v = map(int, input().split())
    snake[u] = v

# 범위 내에 snake 또는 ladder가 있는 경우 혹은 1자로 최대한 간다
# dq : [ (position, move), (), ... ]
dq = deque()
dq.append((1, 0))
check = [False] * 101
check[1] = True
while dq:
    pos, move = dq.popleft()
    if pos == 100:
        print(move)
        break
    elif pos >= 94:
        print(move + 1)
        break

    max_normal = 0
    for i in range(pos + 1, pos + 7):
        l = ladder[i]
        s = snake[i]
        if l:
            if not check[l]:
                check[l] = True
                dq.append((l, move + 1))
        elif s:
            if not check[s]:
                check[s] = True
                dq.append((s, move + 1))
        else:
            max_normal = i

    if max_normal and not check[max_normal]:
        dq.append((max_normal, move + 1))


