N = int(input())
nums = list(map(int, input().split()))
answer = [0] * N
stack = []

for i, num in enumerate(nums):
    while stack and stack[-1][1] < num:
        j, _ = stack.pop()
        answer[j] = num
    stack.append((i, num))

for i, _ in stack:
    answer[i] = -1

for nge in answer:
    print(nge, end=' ')