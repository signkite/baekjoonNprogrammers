mans = []
for _ in range(int(input())):
    age, name = input().split()
    mans.append((int(age), name))
mans.sort(key=lambda x: x[0])
for age, name in mans:
    print(age, name)