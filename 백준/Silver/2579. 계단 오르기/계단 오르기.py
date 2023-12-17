N = int(input())
stairs = []
for _ in range(N):
    stairs.append(int(input()))

score = [0] * N
if N == 1:
    print(stairs[0])
elif N == 2:
    print(stairs[0] + stairs[1])
elif N == 3:
    print(max(stairs[0], stairs[1]) + stairs[2])
else:
    score[0] = stairs[0]
    score[1] = stairs[0] + stairs[1]
    score[2] = max(stairs[0], stairs[1]) + stairs[2]
    for i in range(3, N):
        score[i] = max(score[i - 3] + stairs[i - 1], score[i - 2]) + stairs[i]
    print(score[N - 1])