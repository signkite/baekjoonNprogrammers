from collections import deque

T = int(input())


# list 형태의 str을 deque로 변환
def str2dq(s):
    if s == '[]':
        return []
    return deque(map(int, s[1:-1].split(',')))


# 문제 형식에 맞게 배열을 출력
def print_list(target_list):
    if not target_list:
        print('[]')
        return

    target_list = list(target_list)
    print('[', end='')
    for item in target_list[:-1]:
        print(item, end=',')
    print(target_list[-1], end=']\n')


for _ in range(T):
    # 입력 받기
    p = input()
    n = int(input())
    arr = str2dq(input())

    # 현재 뒤집혀있는지 (True라면 D연산시 배열의 맨 뒤를 없앤다)
    rev_mode = False
    err = False
    for operation in p:
        if operation == 'R':
            rev_mode = not rev_mode
        elif not arr:
            err = True
            break
        elif rev_mode:
            arr.pop()
        else:
            arr.popleft()

    if err:
        print('error')
    else:
        if rev_mode:
            arr.reverse()
        print_list(arr)
