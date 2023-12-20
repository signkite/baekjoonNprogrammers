# 전선의 길이가 나열된 배열 arr로부터
# x의 길이로 랜선을 잘랐을 때 몇 개의 랜선이 나올 수 있는지 반환
def count(arr, x):
    total = 0
    for length in arr:
        if length >= x:
            total += length // x
    return total


K, N = map(int, input().split())
lines = []
for _ in range(K):
    lines.append(int(input()))
lines.sort()

start = 0
end = lines[-1]
mid = (start + end) // 2

# 종료 조건:
# N개 이상을 만드는 최대 길이에 end가 존재하는 상황에서 start가 end를 오른쪽으로 넘어가거나
# 만들지 못하는 최소값에 start가 존재하는 상황에서 end가 start를 왼쪽으로 넘어가면 종료한다.
while start <= end:
    # 현재 자르는 길이(mid) 로 몇 개 만들 수 있는지 구하기
    if mid == 0:
        break
    line_count = count(lines, mid)

    # N보다 적게 만들어지면 자르는 길이를 줄인다.
    if line_count < N:
        end = mid - 1
    # N보다 많이 만들어지면 자르는 길이를 늘린다.
    # 정확히 N만큼 만들어져도 가능한 최대 길이를 구하기 위해 자르는 길이를 늘린다.
    else:
        start = mid + 1
    mid = (start + end) // 2

print(end)
