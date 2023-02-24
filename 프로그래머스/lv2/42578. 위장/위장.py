from collections import defaultdict

def solution(clothes):
    all_wears = defaultdict(list)
    for name, type in clothes:
        all_wears[type].append(name)
        
    answer = 1
    for key in all_wears:
        answer *= len(all_wears[key]) + 1
    
    return answer - 1