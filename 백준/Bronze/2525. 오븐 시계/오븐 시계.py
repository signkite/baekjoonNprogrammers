A, B = map(int, input().split())
C = int(input())

total_min = A * 60 + B + C
hour = total_min // 60 % 24
minute = total_min % 60
print(hour, minute)