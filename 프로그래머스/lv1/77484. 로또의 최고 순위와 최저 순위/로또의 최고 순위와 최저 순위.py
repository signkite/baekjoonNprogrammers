def solution(lottos, win_nums):
    hit = 0
    unknown = 0
    for n in lottos:
        if n == 0:
            unknown += 1
        elif n in win_nums:
            hit += 1
    
    max_hit = hit + unknown
    if max_hit > 6:
        max_hit == 6
    
    answer = []
    if max_hit >= 2:
        answer.append(7 - max_hit)
    else:
        answer.append(6)
        
    if hit >= 2:
        answer.append(7 - hit)
    else:
        answer.append(6)
    
    return answer