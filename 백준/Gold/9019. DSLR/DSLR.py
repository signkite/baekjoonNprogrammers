from collections import deque

T = int(input())


def operation(n, op):
    if op == 'D':
        return (2 * n) % 10000
    elif op == 'S':
        return n - 1 if n else 9999
    elif op == 'L':
        return n // 1000 + (n % 1000) * 10
    else:
        return (n % 10) * 1000 + n // 10


for _ in range(T):
    A, B = map(int, input().split())
    # check[x] : x의 최소 횟수가 확인 되었는지 여부
    check = [False] * 10000

    dq = deque()
    dq.append((A, ''))
    check[A] = True
    while dq:
        num, opers = dq.popleft()
        if num == B:
            print(opers)
            break

        for op in 'DSLR':
            result = operation(num, op)
            if check[result]:
                continue
            check[result] = True
            dq.append((result, opers + op))
            