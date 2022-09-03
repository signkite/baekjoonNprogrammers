#include<iostream>
#include<string>
#include<algorithm>
using namespace std;

int N, M;
string s1[10000];
string s2[10000];

bool binary_search(string s, int start, int end) {
	if (start > end)
		return false;
	
	int mid = (start + end) / 2;

	if (s1[mid] == s)
		return true;
	else if (s1[mid] < s)
		return binary_search(s, mid + 1, end);
	else
		return binary_search(s, start, mid - 1);
}


int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M;
	for (int i = 0; i < N; ++i)
		cin >> s1[i];
	for (int i = 0; i < M; ++i)
		cin >> s2[i];

	sort(s1, s1 + N);

	int cnt = 0;
	for (int i = 0; i < M; ++i) {
		if (binary_search(s2[i], 0, N - 1))
			cnt++;
	}

	cout << cnt;
	return 0;
}