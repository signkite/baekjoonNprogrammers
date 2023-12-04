from itertools import permutations


def calc_dist(pos1, pos2):
    return abs(pos1[0] - pos2[0]) + abs(pos1[1] - pos2[1])


T = int(input())
for test_case in range(1, T + 1):
    N = int(input())

    # 입력값 받기
    company = []
    home = []
    customers = []
    input_nums = list(map(int, input().split()))
    for i, e in enumerate(input_nums):
        if 0 <= i <= 1:
            company.append(e)
        elif 2 <= i <= 3:
            home.append(e)
        elif i % 2 == 0:
            coord = [e]
        else:
            coord.append(e)
            customers.append(coord)

    answer = 30000
    for visit in permutations(customers):
        prev = company
        distance = 0
        for pos in visit:
            distance += calc_dist(prev, pos)
            prev = pos
        distance += calc_dist(prev, home)
        if answer > distance:
            answer = distance

    print(f"#{test_case} {answer}")