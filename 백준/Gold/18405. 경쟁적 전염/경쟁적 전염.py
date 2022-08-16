import sys
from collections import deque
input = sys.stdin.readline

N, K = map(int, input().split())

board = []
for _ in range(N):
    board.append(list(map(int, input().split())))

S, X, Y = map(int, input().split())


def find(x, y):
    if 0 <= x < N and 0 <= y < N:
        return board[x][y]
    else:
        return 0


def find_sec(x, y, s):
    res = set()

    p1 = (s, 0)
    p2 = (-s, 0)
    p3 = (0, s)
    p4 = (0, -s)

    for i in range(0, s):
        nx, ny = x + p1[0] - i, y + p1[1] + i
        res.add(find(nx, ny))
        nx, ny = x + p2[0] + i, y + p2[1] - i
        res.add(find(nx, ny))
        nx, ny = x + p3[0] - i, y + p3[1] - i
        res.add(find(nx, ny))
        nx, ny = x + p4[0] + i, y + p4[1] + i
        res.add(find(nx, ny))

    return list(res)


if board[X - 1][Y - 1]:
    print(board[X - 1][Y - 1])
else:
    min_val = 0
    for sec in range(1, S + 1):
        germ_list = find_sec(X - 1, Y - 1, sec)
        if len(germ_list) > 1:
            germ_list.sort()
            germ_list.pop(0)
            min_val = germ_list[0]
            break

    if min_val:
        print(min_val)
    else:
        print(0)
