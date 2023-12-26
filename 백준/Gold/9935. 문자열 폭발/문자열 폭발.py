import sys

string = sys.stdin.readline().strip()
bomb = sys.stdin.readline().strip()
bomb_length = len(bomb)

# 첫 시도: replace 함수를 사용, 시간초과 발생
# 두 번째 시도: 괄호 짝맞추기 문제와 비슷하다 생각, stack 으로 풀이를 접근
stack = []
for i in range(len(string)):
    stack.append(string[i])
    if ''.join(stack[-bomb_length:]) == bomb:
        for _ in range(bomb_length):
            stack.pop()

if stack:
    print(''.join(stack))
else:
    print('FRULA')
