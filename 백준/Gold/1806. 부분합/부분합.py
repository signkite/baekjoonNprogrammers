N, S = map(int, input().split())
nums = list(map(int, input().split()))

min_len = 100000
start = end = 0
cur_sum = nums[0]

while True:
    if start > end:
        break

    if cur_sum >= S:
        min_len = min(min_len, end - start + 1)
        cur_sum -= nums[start]
        start += 1
    elif end >= N - 1:
        break
    else:
        end += 1
        cur_sum += nums[end]

print(0 if min_len == 100000 else min_len)