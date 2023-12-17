N, M = map(int, input().split())
paper = [list(input()) for _ in range(3 * N)]

for i in range(N):
    for j in range(M):
        # 문제의 가장 왼쪽 위 행, 열의 인덱스
        row = 3 * i
        col = 8 * j

        a = int(paper[row + 1][col + 1])
        b = int(paper[row + 1][col + 3])
        if paper[row + 1][col + 6] == '.':
            c = int(paper[row + 1][col + 5])
            ten = False
        else:
            c = int(''.join(paper[row + 1][col + 5: col + 7]))
            ten = True

        if a + b == c:
            if ten:
                for k in range(col + 1, col + 7):
                    paper[row][k] = '*'
                    paper[row + 2][k] = '*'
                paper[row + 1][col] = paper[row + 1][col + 7] = '*'
            else:
                for k in range(col + 1, col + 6):
                    paper[row][k] = '*'
                    paper[row + 2][k] = '*'
                paper[row + 1][col] = paper[row + 1][col + 6] = '*'
        else:
            paper[row][col + 3] = '/'
            paper[row + 1][col + 2] = '/'
            paper[row + 2][col + 1] = '/'

for line in paper:
    for c in line:
        print(c, end='')
    print()

