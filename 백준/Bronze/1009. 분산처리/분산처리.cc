#include<iostream>

int main() {
	int T;
	int a, b;
	int temp;
	scanf("%d", &T);
	for (int i = 0; i < T; ++i) {
		scanf("%d %d", &a, &b);
		
		temp = 1;
		for (int j = 0; j < b; ++j) {
			temp *= a;
			temp %= 10;
		}
		if (temp == 0)
			printf("10\n");
		else 
			printf("%d\n", temp);
	}

	return 0;
}