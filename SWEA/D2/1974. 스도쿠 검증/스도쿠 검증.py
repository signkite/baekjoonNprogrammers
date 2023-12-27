T = int(input())


# 인자로 전달받은 내용에 1 ~ 9 숫자가 모두 있는지 여부를 bool 값으로 반환
def check_nums(nums):
    check = [False] * 9
    for n in nums:
        check[n - 1] = True
    return True if all(check) else False


for test_case in range(1, T + 1):
    board = [list(map(int, input().split())) for _ in range(9)]
    error = False

    # 가로줄 검증
    for line in board:
        if not check_nums(line):
            error = True

    # 세로줄 검증
    if not error:
        for line in zip(*board):
            if not check_nums(line):
                error = True

    # 3 * 3 격자 검증
    if not error:
        for i in range(3):
            for j in range(3):
                row, col = 3 * i, 3 * j
                nums = []
                for k in range(3):
                    nums += board[row + k][col: col + 3]
                if not check_nums(nums):
                    error = True

    answer = 0 if error else 1
    print(f"#{test_case} {answer}")