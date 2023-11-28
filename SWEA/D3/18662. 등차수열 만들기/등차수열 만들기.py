T = int(input())

for test_case in range(1, T + 1):
    arr = list(map(int, input().split()))
    answer = 1001

    x1 = abs(2 * arr[1] - arr[2] - arr[0])
    x2 = abs((arr[2] + arr[0]) / 2 - arr[1])
    x3 = abs(2 * arr[1] - arr[0] - arr[2])

    x_min = min([x1, x2, x3])
    print("#{0} {1:.1f}".format(test_case, x_min))
