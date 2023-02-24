def solution(n, lost, reserve):
    # lost_list[i] == True : i 번째 사람은 체육복이 없음
    # lost_list[0]과 lost_list[n]은 indexError 방지용 여유 공간
    lost_list = [False] * (n + 2)
    for i in lost:
        lost_list[i] = True
        
    reserve.sort()
    for i in reserve:
        # 자기 자신이 체육복이 없다면 자신의 여유분으로 충당하여 빌려줄 수 없음
        if lost_list[i]:
            lost_list[i] = False
        
        # 앞 번호부터 받을 수 있는 사람에게 체육복 전달
        elif lost_list[i - 1]:
            lost_list[i - 1] = False
        elif lost_list[i + 1] and (i + 1) not in reserve:
            lost_list[i + 1] = False
    return n - sum(lost_list)