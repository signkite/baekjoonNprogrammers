N, M = map(int, input().split())
trees = list(map(int, input().split()))
trees.sort()


# 높이를 h로 설정했을 때 얻을 수 있는 총 나무 길이
def get_log(h):
    total = 0
    for length in trees:
        if length > h:
            total += length - h
    return total


# 이분 탐색으로 가져갈 수 있는 나무의 길이를 탐색
start = 0
end = trees[-1]
height = (start + end) // 2

while start <= end:
    total_log = get_log(height)

    if total_log == M:
        break
    elif total_log < M:
        end = height - 1
    else:
        start = height + 1

    height = (start + end) // 2

print(height)