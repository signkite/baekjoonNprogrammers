def solution(sequence, k):
    start = 0
    end = len(sequence) - 1
    num_sum = 0
    for i in range(len(sequence) - 1, -1, -1):
        start = i
        num_sum += sequence[i]
        if num_sum == k:
            break
        elif num_sum > k:
            num_sum -= sequence[end]
            end -= 1
        
    while start > 0 and sequence[start - 1] == sequence[end]:
        start -= 1
        end -= 1
    
    return start, end