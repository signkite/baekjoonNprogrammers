#include<iostream>
#include<algorithm>
using namespace std;
int N;
int attr[100000];
int min_mix = 2000000000;

int abs(int x) {
	if (x >= 0) return x;
	else return -x;
}

int main() {
	scanf("%d", &N);

	for (int i = 0; i < N; ++i) {
		scanf("%d", attr + i);
	}

	sort(attr, attr + N);

	if (attr[0] >= 0) {
		printf("%d %d", attr[0], attr[1]);
	}
	else if (attr[N - 1] <= 0) {
		printf("%d %d", attr[N - 2], attr[N - 1]);
	}
	else {
		int front = 0;
		int back = N - 1;
		int min_front = front, min_back = back;
		int mix;
		bool flag = false;
		while (front != back) {
			mix = attr[front] + attr[back];
			if (mix == 0) {
				flag = true;
				break;
			}
			else if (mix > 0) {
				if (min_mix > mix) {
					min_front = front;
					min_back = back;
					min_mix = mix;
				}
				back--;
			}
			else {
				if (min_mix > abs(mix)) {
					min_front = front;
					min_back = back;
					min_mix = abs(mix);
				}
				front++;
			}
		}
		if (flag) {
			printf("%d %d", attr[front], attr[back]);
		}
		else {
			printf("%d %d", attr[min_front], attr[min_back]);
		}
	}

	return 0;
}