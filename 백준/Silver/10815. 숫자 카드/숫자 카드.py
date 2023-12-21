# 첫 번째 시도: 해시맵으로 접근 -> 메모리 초과 발생
# 두 번째 시도: 이진 탐색으로 접근

# a 리스트에 숫자 x가 존재하는지 여부를 반환
def bs(a, x):
    start = 0
    end = len(a) - 1
    while start <= end:
        mid = (start + end) // 2
        if a[mid] < x:
            start = mid + 1
        elif a[mid] > x:
            end = mid - 1
        else:
            return True
    return False


input()
card = sorted(list(map(int, input().split())))

input()
for num in map(int, input().split()):
    if bs(card, num):
        print('1', end=' ')
    else:
        print('0', end=' ')
