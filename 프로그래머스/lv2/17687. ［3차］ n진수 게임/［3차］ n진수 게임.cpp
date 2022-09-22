#include <string>
#include <vector>
#include <cmath>
#include <iostream>

using namespace std;

string number(int num, int notation) {
    string symbol = "0123456789ABCDEF";
    string ret = "";
    
    if (num == 0) return "0";
    while (num > 0) {
        ret = symbol[num % notation] + ret;
        num /= notation;
    }
    
    return ret;
}

string solution(int n, int t, int m, int p) {
    string answer = "";
    string str_list = "";
    int i = 0;
    while(str_list.length() <= m * t) {
        str_list += number(i, n);
        i++;
    }
    int order;
    for(int i = 0; i < t; i++) {
        order = m * i + p;
        answer += str_list[order - 1];   
    }
    
    return answer;
}