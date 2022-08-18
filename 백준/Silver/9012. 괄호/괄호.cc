#pragma warning (disable:4996)
#include <iostream>
using namespace std;

int main() {
	char str[51];
	int n;
	int point;
	int top;
	bool breaked;

	scanf("%d", &n);
	for (int i = 0; i < n; ++i) {
		scanf(" %s", str);
		point = 0;
		top = 0;
		breaked = false;
		while (str[point] != '\0')
			if (str[point++] == '(')
				top++;
			else
				if (top == 0) {
					breaked = true;
					break;
				}
				else
					top--;

		if (breaked)
			printf("NO\n");
		else
			if (top)
				printf("NO\n");
			else
				printf("YES\n");
	}

	return 0;
}