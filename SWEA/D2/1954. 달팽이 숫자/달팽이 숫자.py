T = int(input())

for test_case in range(1, T + 1):
    N = int(input())

    step = N - 1
    snail = [[0] * N for _ in range(N)]
    row = col = 0
    num = 1
    while step > 0:
        for _ in range(step):
            snail[row][col] = num
            col += 1
            num += 1
        for _ in range(step):
            snail[row][col] = num
            row += 1
            num += 1
        for _ in range(step):
            snail[row][col] = num
            col -= 1
            num += 1
        for _ in range(step):
            snail[row][col] = num
            row -= 1
            num += 1
        row += 1
        col += 1
        step -= 2
    if N % 2 == 1:
        snail[row][col] = num

    print('#' + str(test_case))
    for line in snail:
        for i in line:
            print(i, end=' ')
        print()
