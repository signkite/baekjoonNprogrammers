N = 0

def dec2binstr(num):
    res = ''
    for _ in range(N):
        if num % 2:
            res = '#' + res
        else:
            res = ' ' + res
        num //= 2
    return res
    
def solution(n, arr1, arr2):
    global N
    N = n
    answer = []
    arr1 = list(map(dec2binstr, arr1))
    arr2 = list(map(dec2binstr, arr2))
    for i in range(n):
        line = ''
        for j in range(n):
            if arr1[i][j] == ' ' and arr2[i][j] == ' ':
                line += ' '
            else:
                line += '#'
        answer.append(line)
    return answer