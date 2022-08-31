#include<iostream>
#include<string>
#include<algorithm>
using namespace std;

bool compare(char c1, char c2) {
	return c1 > c2;
}

int main(){
	string num;
	cin >> num;
	sort(num.begin(), num.end(), compare);
	cout << num;

	return 0;
}