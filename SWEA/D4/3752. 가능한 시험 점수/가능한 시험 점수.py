T = int(input())

for test_case in range(1, T + 1):
    N = int(input())
    nums = list(map(int, input().split()))

    # possible[n]: n이 가능한 숫자라면 True
    possible = [False] * 10001

    # mult[n]: n이 2개 이상 존재하면 True
    mult = [False] * 10001

    for n in nums:
        if possible[n]:
            mult[n] = True
        else:
            possible[n] = True

        temp = possible.copy()
        for i, p in enumerate(possible):
            if i == n and not mult[n]:
                continue

            if p:
                temp[i + n] = True

        possible = temp

    # 0점이 있으므로 +1개
    answer = 1
    for p in possible:
        if p:
            answer += 1
    print(f"#{test_case} {answer}")
