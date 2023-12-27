T = int(input())

for test_case in range(1, T + 1):
    N, M = map(int, input().split())
    A = list(map(int, input().split()))
    B = list(map(int, input().split()))

    # A가 B보다 긴 숫자열이 되도록 경우를 통일한 후 연산
    if N < M:
        N, M = M, N
        A, B = B, A

    nums = []
    for i in range(N - M + 1):
        A_frac = A[i: i + M]
        num = 0
        for j in range(M):
            num += A_frac[j] * B[j]
        nums.append(num)

    print(f"#{test_case} {max(nums)}")