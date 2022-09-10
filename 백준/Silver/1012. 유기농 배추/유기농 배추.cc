#include<iostream>
#include<queue>
#include<utility>
using namespace std;

int T, M, N, K, X, Y;
int dx[4] = {0, 0, 1, -1};
int dy[4] = {1, -1, 0, 0};
int field[50][50];
bool check[50][50];
int cnt;
queue<pair<int, int>> q;

void bfs(int y, int x) {
	if (field[y][x] == 0 || check[y][x])
		return;

	q.push(make_pair(y, x));
	check[y][x] = true;
	cnt++;

	int ny, nx;
	int cy, cx;
	while (!q.empty()) {
		cy = q.front().first;
		cx = q.front().second;
		q.pop();

		for (int i = 0; i < 4; ++i) {
			ny = cy + dy[i];
			nx = cx + dx[i];

			if (0 <= ny && ny <= N - 1 && 0 <= nx && nx <= M - 1 && field[ny][nx] == 1 && !check[ny][nx]) {
				check[ny][nx] = true;
				q.push(make_pair(ny, nx));
			}
		}
	}
}

// 다음 test case로 넘어갈 때 check, field, cnt 초기화
void reset(){
	for (int i = 0; i < N; ++i) {
		for (int j = 0; j < M; ++j) {
			check[i][j] = false;
			field[i][j] = 0;
		}
	}
	cnt = 0;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> T;

	for (int t = 0; t < T; ++t) {
		cin >> M >> N >> K;
		for (int k = 0; k < K; ++k) {
			cin >> X >> Y;
			field[Y][X] = 1;
		}

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				bfs(i, j);
			}
		}
		cout << cnt << '\n';
		reset();
	}

	return 0;
}