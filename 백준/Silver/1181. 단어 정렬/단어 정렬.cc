#include<iostream>
#include<algorithm>
using namespace std;
int N;
string s_arr[20000];

bool compare(string s1, string s2) {
	if (s1.length() == s2.length())
		return s1 < s2;
	else
		return s1.length() < s2.length();
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N;

	for (int i = 0; i < N; ++i) {
		cin >> s_arr[i];
	}

	sort(s_arr, s_arr + N, compare);

	string previous_str = "";
	for (int i = 0; i < N; ++i) {
		if (previous_str == s_arr[i])
			continue;

		cout << (previous_str = s_arr[i]) << endl;
	}

	return 0;
}
