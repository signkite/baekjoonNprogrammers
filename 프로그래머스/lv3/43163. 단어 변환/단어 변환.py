from collections import deque

# 두 단어의 철자 차이가 1개면 True 아니라면 False 반환
def diff1(word1, word2):
    cnt = 0
    for i in range(len(word1)):
        if word1[i] != word2[i]:
            cnt += 1
            
    if cnt == 1:
        return True
    else:
        return False
        

def solution(begin, target, words):
    answer = 0
    check = [False] * len(words)
    
    dq = deque([(begin, 0)])
    
    while dq:
        cur_word, cnt = dq.popleft()
        
        for i in range(len(words)):
            if not check[i] and diff1(cur_word, words[i]):
                if words[i] == target:
                    return cnt + 1
                check[i] = True
                dq.append((words[i], cnt + 1))
                
    return 0