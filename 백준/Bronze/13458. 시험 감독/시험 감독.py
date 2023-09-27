N = int(input())
A = list(map(int, input().split()))
B, C = list(map(int, input().split()))

# 총감독관 1명이 커버할 수 있는 수 제외
A = list(map(lambda x: x - B, A))

answer = len(A)
for num in A:
    if num > 0:
        answer += num // C if num % C == 0 else num // C + 1

print(answer)