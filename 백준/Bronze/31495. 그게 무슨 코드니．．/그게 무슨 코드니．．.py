string = input()
if len(string) > 2 and string[0] == '"' and string[-1] == '"':
    print(string[1:-1])
else:
    print('CE')