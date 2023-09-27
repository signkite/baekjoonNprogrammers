from collections import defaultdict
def solution(participant, completion):
    check_dict = defaultdict(int)
    for name in participant:
        check_dict[name] += 1
    
    for name in completion:
        check_dict[name] -= 1
    
    for key in check_dict:
        if check_dict[key] > 0:
            return key