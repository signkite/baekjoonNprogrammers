# 입력 받기
N, C = map(int, input().split())
home = []
for _ in range(N):
    home.append(int(input()))

# 집 위치를 오름차순으로 정렬
home.sort()


# 거리 n 이상이 보장되는 경우에만 공유기를 설치했을 때의
# 공유기 개수를 반환
def count(n):
    total = 1
    prev = home[0]
    for h in home[1:]:
        if h - prev >= n:
            prev = h
            total += 1
    return total


# parametric search를 이용해 공유기 거리의 최대값을 찾는다.
# 특정 거리 n 이상의 간격이 보장될 때만 공유기를 설치한다 가정, 그 때의 공유기 개수를 구한다.
# 목적 보다 공유기 개수가 많거나 같다면 -> 거리를 늘린다. / 적다면 -> 거리를 줄인다.
start = 1
end = (home[-1] - home[0]) // (C - 1)

while start <= end:
    mid = (end + start) // 2
    if count(mid) < C:
        end = mid - 1
    else:
        start = mid + 1

print(end)
