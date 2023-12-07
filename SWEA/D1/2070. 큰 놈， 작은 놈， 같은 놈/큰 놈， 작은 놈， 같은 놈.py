T = int(input())

for test_case in range(1, T + 1):
    n, m = map(int, input().split())
    if n > m:
        answer = '>'
    elif n == m:
        answer = '='
    else:
        answer = '<'
    print(f'#{test_case} {answer}')