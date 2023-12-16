from collections import Counter
T = int(input())
for _ in range(1, T + 1):
    test_case = int(input())
    counter = Counter(list(map(int, input().split())))
    print(f'#{test_case} {counter.most_common()[0][0]}')