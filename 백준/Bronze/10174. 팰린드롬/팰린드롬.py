n = int(input())
for _ in range(n):
    text = input().lower()
    rev_text = text[::-1]
    if text == rev_text:
        print("Yes")
    else:
        print("No")