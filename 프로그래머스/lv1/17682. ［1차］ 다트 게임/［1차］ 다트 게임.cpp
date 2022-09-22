#include <string>

using namespace std;

int solution(string dartResult) {
    int answer = 0;
    int former_num = 0;
    int num = 0;
    int cnt = 0, i = 0;
    
    for(int i = 0; i < dartResult.length(); ++i) {
        char dr = dartResult[i];
        if ('0' <= dr && dr <= '9') {
            answer += num;
            former_num = num;
            if (dr == '1' && dartResult[i + 1] == '0') {
                num = 10;
                i++;
            }
            else {
                num = dr - '0';
            }            
        }
        else if (dr == 'D') {
            num = num * num;
        }
        else if (dr == 'T') {
            num = num * num * num;
        }
        else if (dr == '*') {
            answer += former_num;
            num *= 2;
        }
        else if (dr == '#') {
            num = -num;
        }
    }
    answer += num;
    
    return answer;
}