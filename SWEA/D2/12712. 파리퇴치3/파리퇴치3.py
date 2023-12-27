plus_move = [(-1, 0), (1, 0), (0, 1), (0, -1)]
x_move = [(-1, -1), (1, 1), (-1, 1), (1, -1)]

T = int(input())

for test_case in range(1, T + 1):
    N, M = map(int, input().split())
    matrix = [list(map(int, input().split())) for _ in range(N)]

    max_flies = 0
    for i in range(N):
        for j in range(N):
            x = plus = matrix[i][j]
            for k in range(1, M):
                # x모양 노즐로 잡을 수 있는 파리 수 계산
                for n, m in plus_move:
                    if 0 <= i + n * k < N and 0 <= j + m * k < N:
                        x += matrix[i + n * k][j + m * k]
                # +모양 노즐로 잡을 수 있는 파리 수 계산
                for n, m in x_move:
                    if 0 <= i + n * k < N and 0 <= j + m * k < N:
                        plus += matrix[i + n * k][j + m * k]
            # 최대값 갱신
            max_flies = max(x, plus, max_flies)

    print(f"#{test_case} {max_flies}")