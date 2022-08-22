#include <iostream>
#include <algorithm>
using namespace std;

int A[100000];
int arr[100000];
int N, M;

bool binary_search(int start, int end, int dst) {
	if (start > end) {
		return false;
	}
	
	int mid = (start + end) / 2;

	if (A[mid] == dst) {
		return true;
	}
	else if (A[mid] < dst) {
		return binary_search(mid + 1, end, dst);
	}
	else {
		return binary_search(start, mid - 1, dst);
	}
}

int main() {
	scanf("%d", &N);
	for (int i = 0; i < N; ++i) {
		scanf("%d", A + i);
	}

	scanf("%d", &M);
	for (int i = 0; i < M; ++i) {
		scanf("%d", arr + i);
	}

	sort(A, A + N);  // sort for binary search

	for (int i = 0; i < M; ++i) {
		printf("%d\n", binary_search(0, N - 1, arr[i]) ? 1 : 0);
	}

	return 0;
}