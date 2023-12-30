import sys
input = sys.stdin.readline
move = [(-1, 0), (1, 0), (0, 1), (0, -1)]


def dfs(x, y, length):
    global answer
    answer = max(answer ,length)

    for dx, dy in move:
        nx, ny = x + dx, y + dy
        if 0 <= nx < R and 0 <= ny < C and board[nx][ny] not in string:
            string.add(board[nx][ny])
            dfs(nx, ny, length + 1)
            string.pop()


R, C = map(int, input().split())
board = [list(input()) for _ in range(R)]
answer = 1
string = set(board[0][0])
dfs(0, 0, 1)
print(answer)