T = int(input())

for test_case in range(1, T + 1):
    nums = list(map(int, input().split()))
    avg = sum(nums) / len(nums)
    unders = avg - int(avg)
    if unders < 0.5:
        answer = int(avg)
    else:
        answer = int(avg) + 1
        
    print("#{0} {1}".format(test_case, answer))
