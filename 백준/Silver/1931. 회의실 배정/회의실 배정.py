import sys
input = sys.stdin.readline

N = int(input())

meeting = []
for _ in range(N):
    meeting.append(tuple(map(int, input().split())))

# 끝나는 시간이 제일 이른 스케쥴 순으로 정렬
# 끝나는 시간이 같다면 시작 시간이 이른 스케쥴 순으로 정렬
# (시작 시간 == 끝 시간) 인 회의가 존재시 이를 횟수에 넣어줘야 하므로)
meeting.sort(key=lambda x: (x[1], x[0]))

_, cur_end = meeting[0]
answer = 1
for start, end in meeting[1:]:
    if cur_end <= start:
        answer += 1
        cur_end = end

print(answer)