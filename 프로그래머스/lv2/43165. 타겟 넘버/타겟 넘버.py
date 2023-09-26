def solution(numbers, target):
    answer = 0
    
    def dfs(index, cur_sum):
        nonlocal answer
        
        if index == len(numbers):
            if cur_sum == target:
                answer += 1
            return
        
        dfs(index + 1, cur_sum + numbers[index])
        dfs(index + 1, cur_sum - numbers[index])
        
    dfs(0, 0)
    
    return answer