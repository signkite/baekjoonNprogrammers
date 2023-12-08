T = int(input())

for test_case in range(1, T + 1):
    N, K = map(int, input().split())
    input_num = input()

    # N / 4 회만큼 회전하면 모든 경우 확인 가능
    num_set = set()
    for _ in range(N // 4):
        new_nums = {input_num[: N // 4],
                    input_num[N // 4: N // 2],
                    input_num[N // 2: N * 3 // 4],
                    input_num[N * 3 // 4:]}
        num_set.update(new_nums)
        input_num = input_num[-1] + input_num[:-1]

    num_list = list(map(lambda x: int(x, 16), num_set))
    num_list.sort(reverse=True)

    print(f'#{test_case} {num_list[K - 1]}')