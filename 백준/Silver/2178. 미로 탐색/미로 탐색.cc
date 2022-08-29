#include<iostream>
#include<queue>
using namespace std;

int N, M;
char maze[100][100];
int cnt[100][100] = { 0 };
int dx[4] = { 0, 0, 1, -1 };
int dy[4] = { 1, -1, 0, 0 };
queue<pair<int, int>> q;

void bfs() {
	q.push({0, 0});
	int x, y;
	int nx, ny;

	while (!q.empty()) {
		x = q.front().first;
		y = q.front().second;
		q.pop();

		if (x == N - 1 && y == M - 1) {
			printf("%d", cnt[x][y] + 1);
			return;
		}

		for (int i = 0; i < 4; ++i) {
			nx = x + dx[i];
			ny = y + dy[i];

			if (0 <= nx && nx < N && 0 <= ny && ny < M && maze[nx][ny] == '1' && cnt[nx][ny] == 0) {
				cnt[nx][ny] = cnt[x][y] + 1;
				q.push({ nx,ny });
			}
		}
	}
	return;
}

int main() {
	scanf("%d %d", &N, &M);
	getchar();
	for (int i = 0; i < N; ++i) {
		for (int j = 0; j < M; ++j) {
			maze[i][j] = getchar();
		}
		getchar();
	}

	bfs();
	return 0;
}