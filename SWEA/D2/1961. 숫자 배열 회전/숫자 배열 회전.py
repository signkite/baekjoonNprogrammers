T = int(input())


# 인자로 전달한 행렬을 시계방향으로 90도 회전한 행렬을 반환
def rotate_matrix(matrix):
    rotated = []
    for vertical_line in zip(*matrix):
        rotated.append(vertical_line[::-1])
    return rotated


for test_case in range(1, T + 1):
    N = int(input())
    mat = [input().split() for _ in range(N)]

    mat1 = rotate_matrix(mat)
    mat2 = rotate_matrix(mat1)
    mat3 = rotate_matrix(mat2)

    print('#' + str(test_case))
    for i in range(N):
        print(''.join(mat1[i]), ''.join(mat2[i]), ''.join(mat3[i]))