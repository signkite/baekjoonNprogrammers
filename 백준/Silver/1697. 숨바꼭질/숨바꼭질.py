from collections import deque

N, K = map(int, input().split())

# stage[n] : n 위치의 최소값이 확인 되었는지 여부
stage = [False] * 200001

if N >= K:
    print(N - K)
else:
    dq = deque([[N, 0]])

    while dq:
        pos, move = dq.popleft()

        if pos == K:
            print(move)
            break

        if stage[pos]:
            continue

        stage[pos] = True

        if pos > 0:
            dq.append([pos - 1, move + 1])
        if pos <= 100000:
            dq.append([pos * 2, move + 1])
            dq.append([pos + 1, move + 1])
