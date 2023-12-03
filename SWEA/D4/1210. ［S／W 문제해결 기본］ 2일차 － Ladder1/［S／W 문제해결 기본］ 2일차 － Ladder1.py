for _ in range(10):
    test_case = input()
    ladder = [input().split() for _ in range(100)]

    # 도착지 좌표 찾기
    row, col = 99, 99
    for i in range(100):
        if ladder[99][i] == '2':
            col = i

    # 도착지로부터 시작점 유추
    while row > 0:
        
        # 왼쪽에 길이 있다면 이동
        if col != 0 and ladder[row][col - 1] == '1':
            while col > 0:
                if ladder[row][col - 1] == '0':
                    break
                col -= 1

        # 오른쪽에 길이 있다면 이동
        elif col != 99 and ladder[row][col + 1] == '1':
            while col < 99:
                if ladder[row][col + 1] == '0':
                    break
                col += 1
        # 위로 한 칸 이동
        row -= 1

    print(f"#{test_case} {col}")