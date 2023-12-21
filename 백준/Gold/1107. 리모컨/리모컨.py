# char_list 속 문자 중 하나라도 string이 포함하고 있다면 True를 반환
def contains(string, char_list):
    for c in char_list:
        if c in string:
            return True
    return False


N = int(input())
M = int(input())
error = input().split() if M != 0 else []

# 모든 숫자가 고장난 경우
if M == 10:
    print(abs(N - 100))

# case A) 목적 채널에서 가능한 숫자 조합이 나올 때까지 +/- 하기
# case B) 100에서 + 혹은 -를 하여 목적 채널로 가기
# A, B 중 적은 횟수를 출력
else:
    case_A = 0
    while True:
        # 체크할 숫자가 음수가 되는 경우 제외
        if case_A <= N and not contains(str(N - case_A), error):
            case_A += len(str(N - case_A))
            break
        elif not contains(str(N + case_A), error):
            case_A += len(str(N + case_A))
            break
        case_A += 1

    case_B = abs(N - 100)
    print(min(case_A, case_B))
