from collections import deque

N = int(input())
A = list(map(int, input().split()))
operators = list(map(int, input().split()))

len_A = len(A)
min_num = 1000000000
max_num = -min_num


def operation(num1, op_num, num2):
    if op_num == 0:
        return num1 + num2
    elif op_num == 1:
        return num1 - num2
    elif op_num == 2:
        return num1 * num2
    else:
        if num1 < 0 < num2:
            return -(-num1 // num2)
        else:
            return num1 // num2


def dfs(index, cur, left):
    global min_num
    global max_num

    if index == len_A - 1:
        if cur < min_num:
            min_num = cur
        if max_num < cur:
            max_num = cur
        return

    for i, op in enumerate(left):
        if op != 0:
            left[i] -= 1
            dfs(index + 1, operation(cur, i, A[index + 1]), left)
            left[i] += 1


dfs(0, A[0], operators)
print(max_num)
print(min_num)
