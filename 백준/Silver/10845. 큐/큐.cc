#pragma warning(disable:4996)
#include<iostream>
#include<cstring>

int q[10000];
int front = 0;
int rear = -1;
char input[10];
int N;
int num;

bool empty() {
    if (front > rear)
        return true;
    else
        return false;
}

int main() {
    scanf("%d", &N);
    for (int i = 0; i < N; ++i) {
        scanf("%s", input);

        if (!strcmp(input, "push")) {
            scanf("%d", &num);
            q[++rear] = num;
        }
        else if (!strcmp(input, "pop")) {
            if (empty()) {
                printf("-1\n");
            }
            else {
                printf("%d\n", q[front++]);
            }
        }
        else if (!strcmp(input, "front")) {
            if (empty()) {
                printf("-1\n");
            }
            else {
                printf("%d\n", q[front]);
            }
        }
        else if (!strcmp(input, "back")) {
            if (empty()) {
                printf("-1\n");
            }
            else {
                printf("%d\n", q[rear]);
            }
        }
        else if (!strcmp(input, "size")) {
            if (empty()) {
                printf("0\n");
            }
            else {
                printf("%d\n", rear - front + 1);
            }
        }
        else if (!strcmp(input, "empty")) {
            if (empty()) {
                printf("1\n");
            }
            else {
                printf("0\n");
            }
        }
    }
    return 0;
}