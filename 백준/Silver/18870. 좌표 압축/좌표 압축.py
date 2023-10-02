from collections import Counter

N = int(input())
X = list(map(int, input().split()))
sorted_X = sorted(X)
# 리스트 정렬후 그걸 키값화 그리고 키값을 다시 리스트화
counter = Counter(sorted_X)
accumulated_counter = {}
total_count = 0
for key in counter:
    accumulated_counter[key] = total_count
    total_count += 1

for x in X:
    print(accumulated_counter[x], end=' ')