#include<iostream>

using namespace std;

int T, n;
int x_s, x_e, y_s, y_e;
int cx, cy, r;

int sq(int n) {
	return n * n;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> T;

	for (int t = 0; t < T; t++) {
		int out_cnt = 0, in_cnt = 0;
		cin >> x_s >> y_s >> x_e >> y_e;
		cin >> n;
		for (int i = 0; i < n; ++i) {
			cin >> cx >> cy >> r;

			if (sq(cx - x_s) + sq(cy - y_s) < sq(r))
				if (sq(cx - x_e) + sq(cy - y_e) > sq(r))
					out_cnt++;

			if (sq(cx - x_e) + sq(cy - y_e) < sq(r))
				if (sq(cx - x_s) + sq(cy - y_s) > sq(r))
					in_cnt++;
		}
		cout << in_cnt + out_cnt << endl;
	}
	return 0;
}