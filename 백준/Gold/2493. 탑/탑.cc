#pragma warning (disable:4996)
#include<iostream>
#include<stack>
using namespace std;

int main() {
	int N;
	int height;
	stack<pair<int, int>> tower;

	scanf("%d", &N);
	for (int i = 0; i < N; ++i) {
		scanf("%d", &height);
		
		if (tower.empty()) {
			tower.push({ height, i + 1 });
			printf("0");
		}
		else {
			if (tower.top().first > height) {
				printf("%d", tower.top().second);
				tower.push({ height, i + 1 });
			}
			else {
				while (!tower.empty() && tower.top().first < height)
					tower.pop();

				if (tower.empty())
					printf("0");
				else
					printf("%d", tower.top().second);

				tower.push({ height, i + 1 });
			}
		}
		if (i < N - 1)
			printf(" ");
	}

	return 0;
}