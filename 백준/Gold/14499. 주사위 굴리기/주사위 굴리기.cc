#include<iostream>

using namespace std;

int map[20][20];
int dice[7];  // 1: 바닥면 / 6: 천장면 / 동서남북: 3, 4, 5, 2
int dx[4] = {0, 0, -1, 1};
int dy[4] = {1, -1, 0, 0};
int N, M, x_put, y_put, K;

// 특정 direction으로 주사위가 굴렀을 때, dice의 상태를 업데이트
void move(int direction) {  
	int temp[7];
	for (int i = 1; i <= 6; ++i) {
		temp[i] = dice[i];
	}

	switch (direction) {
	case 1:
		dice[1] = temp[3]; dice[3] = temp[6];
		dice[4] = temp[1]; dice[6] = temp[4];
		break;
	case 2:
		dice[1] = temp[4]; dice[3] = temp[1];
		dice[4] = temp[6]; dice[6] = temp[3];
		break;
	case 3:
		dice[1] = temp[2]; dice[5] = temp[1];
		dice[6] = temp[5]; dice[2] = temp[6];
		break;
	case 4:
		dice[2] = temp[1]; dice[1] = temp[5];
		dice[5] = temp[6]; dice[6] = temp[2];
		break;
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> N >> M >> x_put >> y_put >> K;
	for (int i = 0; i < N; ++i) {
		for (int j = 0; j < M; ++j) {
			cin >> map[i][j];
		}
	}

	int order;
	int nx, ny;
	for (int i = 0; i < K; ++i) {
		cin >> order;

		nx = x_put + dx[order - 1];
		ny = y_put + dy[order - 1];
		if (0 <= nx && nx <= N - 1 && 0 <= ny && ny <= M - 1) {

			move(order);

			if (map[nx][ny] == 0) {
				map[nx][ny] = dice[1];
			}
			else {
				dice[1] = map[nx][ny];
				map[nx][ny] = 0;
			}

			cout << dice[6] << '\n';  //주사위 윗면 출력

			x_put = nx;
			y_put = ny;

		}
		else
			continue;
	}
	return 0;
}