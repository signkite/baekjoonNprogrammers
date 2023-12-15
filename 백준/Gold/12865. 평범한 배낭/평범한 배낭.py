N, K = map(int, input().split())
arr = []
for _ in range(N):
    arr.append(tuple(map(int, input().split())))

# values[w] : 무게가 w 일 때 가치의 최대값
values = [-1] * (K + 1)
values[0] = 0

for w, v in arr:
    for i in range(K, -1, -1):
        if values[i] == -1 or K < i + w:
            continue

        values[i + w] = max(values[i + w], values[i] + v)

print(max(values))
