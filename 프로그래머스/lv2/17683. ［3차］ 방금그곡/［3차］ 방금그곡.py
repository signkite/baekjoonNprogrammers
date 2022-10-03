def solution(m, musicinfos):
    answer = ''
    answer_play_min = 0
    
    # m의 # 들어간 코드를 모두 소문자로 변경
    changed_m = ''
    for i, code in enumerate(m):
        if code == '#':  # 샵이라면 패스
            continue
        elif i == len(m) - 1:  # 마지막 문자라면, 다음문자 체크 못함 (인덱스 에러 방지)
            changed_m += code
        else:
            if m[i + 1] == '#':  # 다음 문자가 #인지 확인 후, 소문자로 변경할지말지 체크
                changed_m += code.lower()
            else:
                changed_m += code
                
    for musicinfo in musicinfos:
        s_time, e_time, title, codes = musicinfo.split(',')
        s_hour, s_min = s_time.split(':')
        e_hour, e_min = e_time.split(':')
        
        play_min = (int(e_hour) - int(s_hour)) * 60 + int(e_min) - int(s_min)
        
        # 샵 코드를 모두 소문자로 변환
        changed_codes = ''
        for i, code in enumerate(codes):
            if code == '#':  # 샵이라면 패스
                continue
            elif i == len(codes) - 1:  # 마지막 문자라면, 다음문자 체크 못함 (인덱스 에러 방지)
                changed_codes += code
            else:
                if codes[i + 1] == '#':  # 다음 문자가 #인지 확인 후, 소문자로 변경할지말지 체크
                    changed_codes += code.lower()
                else:
                    changed_codes += code
        
        code_len = len(changed_codes)
        
        whole_code = changed_codes * (play_min // code_len) + changed_codes[:play_min % code_len]
        
        if changed_m in whole_code and answer_play_min < play_min:
            answer = title
            answer_play_min = play_min
        
    if answer_play_min == 0:
        answer = '(None)'
    
    return answer