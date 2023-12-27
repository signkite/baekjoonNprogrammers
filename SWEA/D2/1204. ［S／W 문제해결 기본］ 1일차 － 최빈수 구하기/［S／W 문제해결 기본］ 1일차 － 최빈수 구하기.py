T = int(input())

for _ in range(T):
    test_case = input()
    counter = {}
    for num in map(int, input().split()):
        if num not in counter:
            counter[num] = 0
        counter[num] += 1

    # [(숫자, 개수), (숫자, 개수), ... ] 로 구성된 배열을
    # 개수 기준 오름차순으로, 개수가 같다면 숫자 기준 오름차순으로 정렬
    items = sorted(list(counter.items()), key=lambda x: (x[1], x[0]))
    print(f"#{test_case} {items[-1][0]}")
    