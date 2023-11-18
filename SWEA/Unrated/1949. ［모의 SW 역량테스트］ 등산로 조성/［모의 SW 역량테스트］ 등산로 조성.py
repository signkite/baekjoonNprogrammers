T = int(input())

move = [(-1, 0), (1, 0), (0, 1), (0, -1)]

# 지도상의 최대 높이를 찾아 그 값을 반환
def get_max_height(target):
    result = -1
    for line in target:
        for height in line:
            if height > result:
                result = height
    return result


# 지도상의 최대 높이가 있는 위치를 반환
# [[x1 행, y1 열], [x2 행, y2 열], ... ]
def get_peak_locations(target, max_height):
    result = []
    for x in range(len(target)):
        for y in range(len(target)):
            if target[x][y] == max_height:
                result.append([x, y])
    return result


def dfs(x, y, length, cur_height, is_worked):
    global checked
    global max_length

    if length > max_length:
        max_length = length

    for dx, dy in move:
        nx, ny = x + dx, y + dy
        if nx < 0 or ny < 0 or N <= nx or N <= ny:
            continue
        if checked[nx][ny]:
            continue

        # 다음곳이 현재보다 높거나 같다면
        if maps[nx][ny] >= cur_height:
            # 이미 공사 찬스를 소모했다면
            if is_worked:
                continue

            # 최대로 공사해도 현재보다 낮게 만들 수 없다면
            if maps[nx][ny] - K >= cur_height:
                continue

            checked[nx][ny] = True
            dfs(nx, ny, length + 1, cur_height - 1, True)
            checked[nx][ny] = False

        # 다음 곳이 현재보다 낮다면
        else:
            checked[nx][ny] = True
            dfs(nx, ny, length + 1, maps[nx][ny], is_worked)
            checked[nx][ny] = False


for test_case in range(1, T + 1):
    N, K = map(int, input().split())
    maps = [list(map(int, input().split())) for _ in range(N)]

    max_height = get_max_height(maps)
    peak_locations = get_peak_locations(maps, max_height)
    max_length = -1

    checked = [[False] * N for _ in range(N)]
    for x, y in peak_locations:
        checked[x][y] = True
        dfs(x, y, 1, max_height, False)
        checked[x][y] = False

    print(f"#{test_case} {max_length}")