match = {
    "A+": 4.5,
    "A0": 4.0,
    "B+": 3.5,
    "B0": 3.0,
    'C+': 2.5,
    'C0': 2.0,
    'D+': 1.5,
    'D0': 1.0,
    'F': 0
}
total_score = 0  # 총 등급 점수
total_credit = 0  # 총 학점
for _ in range(20):
    _, credit, score = input().split()
    if score == 'P':
        continue

    total_credit += float(credit)
    total_score += match[score] * float(credit)
print(total_score / total_credit)