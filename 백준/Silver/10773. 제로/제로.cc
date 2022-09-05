#include<iostream>
#include<stack>
using namespace std;

stack<int> book;
int sum;
int k;
int num;

int main(){
	scanf("%d", &k);

	for (int i = 0; i < k; ++i) {
		scanf("%d", &num);
		if (num == 0) {
			book.pop();
		}
		else {
			book.push(num);
		}
	}

	while (!book.empty()) {
		sum += book.top();
		book.pop();
	}
	printf("%d", sum);

	return 0;
}