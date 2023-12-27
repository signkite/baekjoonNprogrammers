N = int(input())
T, P = [0], [0]

# dp[x] : x일 까지 일했을 때 벌 수 있는 돈의 최대값
dp = [0] * (N + 1)

for _ in range(N):
    t, p = map(int, input().split())
    T.append(t)
    P.append(p)

for i in range(1, N + 1):
    fin_day = i + T[i] - 1
    dp[i] = max(dp[:i + 1])
    if fin_day <= N:
        dp[fin_day] = max(dp[fin_day], dp[i - 1] + P[i])

print(dp[N])