from collections import defaultdict

dd = defaultdict(int)

N = int(input())
for num in map(int, input().split()):
    dd[num] += 1

M = int(input())
for num in map(int, input().split()):
    print(dd[num], end=' ')