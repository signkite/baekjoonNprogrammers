T = int(input())

# floor[i] : i + 1 층의 시작 숫자
floor = []
i = 1
num = 1
while num <= 10000:
    floor.append(num)
    num += i
    i += 1
floor.append(num)


def calc_floor(n):
    f = 0
    while n >= floor[f]:
        f += 1
    return f


for test_case in range(1, T + 1):
    s, e = map(int, input().split())
    if s == e:
        answer = 0
    else:
        s_floor = calc_floor(s)
        e_floor = calc_floor(e)

        # 아래층, 윗층 찾기 (숫자가 작을 수록 윗층)
        if s_floor < e_floor:
            h, h_floor, l, l_floor = s, s_floor, e, e_floor
        else:
            h, h_floor, l, l_floor = e, e_floor, s, s_floor

        # 층간 이동
        answer = l_floor - h_floor

        # 해당 층에서의 순서 구하기
        h_order = h - floor[h_floor - 1]
        l_order = l - floor[l_floor - 1]

        # 순서 차만큼 좌우 이동
        answer += abs(h_order - l_order)

        # 층간이동 & 좌우이동을 동시에 할 수 있음을 고려
        if h_order < l_order:
            answer -= min([l_floor - h_floor, l_order - h_order])

    print(f"#{test_case} {answer}")