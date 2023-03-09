def solution(arr):
    answer = []
    for elmt in arr:
        if not answer or elmt != answer[-1]:
            answer.append(elmt)
    return answer