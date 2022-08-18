#pragma warning (disable:4996)
#include<iostream>
#include<cstdlib>
#include<cstring>
using namespace std;

int main() {
	int N;
	char command[10000][10];
	char* ptr;
	int val;
	int stack[10000];
	int top = -1;

	scanf("%d ", &N);
	for (int i = 0; i < N; ++i) {
		cin.getline(command[i], 100);

		if (command[i][1] == 'u') {  // push
			strtok(command[i], " ");
			ptr = strtok(NULL, " ");
			val = atoi(ptr);
			stack[++top] = val;
		}
		else if (command[i][0] == 'p') {  // pop
			if (top == -1)
				printf("%d\n", top);
			else
				printf("%d\n", stack[top--]);
		}
		else if (command[i][0] == 't') {  // top
			if (top == -1)
				printf("%d\n", top);
			else
				printf("%d\n", stack[top]);
		}
		else if (command[i][0] == 's') {  // size
			printf("%d\n", top + 1);
		}
		else  // empty
			if (top == -1)
				printf("1\n");
			else
				printf("0\n");
	}

	return 0;
}