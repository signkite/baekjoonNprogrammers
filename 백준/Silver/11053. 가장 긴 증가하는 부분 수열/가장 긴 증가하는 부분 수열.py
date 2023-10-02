N = int(input())

if N == 1:
    print(1)
else:
    A = list(map(int, input().split()))

    dp = [1] * len(A)
    for i in range(len(A)):
        for j in range(i):
            if dp[j] >= dp[i] and A[j] < A[i]:
                dp[i] = dp[j] + 1
    
    print(max(*dp))