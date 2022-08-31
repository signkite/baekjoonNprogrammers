#include<iostream>
#include<utility>
#include<queue>
#include<algorithm>
using namespace std;

int dx[4] = { 0,0,1,-1 };
int dy[4] = { 1,-1,0,0 };
int nx, ny;

int A[50][50];
bool check[50][50]{};  // 확인 한 곳이라면 true
int N, L, R;
int population_sum;
int country_cnt;
int mean;
bool is_moved;
queue<pair<int, int>> q;
queue<pair<int, int>> union_countries;

// x, y 와 연합을 이루는 국가의 수를 반환. 연합을 이루지 않는다면 1이 반환됨
int bfs(int x, int y) {
	if (check[x][y])
		return 0;  // 이미 확인된 곳이라면 pass 하도록 0 반환

	q.push({ x,y });
	check[x][y] = true;
	country_cnt = 0;
	population_sum = 0;

	// 인접 국가를 dfs로 순회하며 연합의 인구 합을 계산
	while (!q.empty()) {
		x = q.front().first;
		y = q.front().second;
		q.pop();
		union_countries.push({x, y});
		
		country_cnt++;
		population_sum += A[x][y];

		for (int i = 0; i < 4; ++i) {
			nx = x + dx[i];
			ny = y + dy[i];

			int dif = abs(A[nx][ny] - A[x][y]);

			if (0 <= nx && nx < N && 0 <= ny && ny < N && !check[nx][ny] && L <= dif && dif <= R) {
				check[nx][ny] = true;
				q.push({ nx,ny });
			}
		}
	}

	if (country_cnt > 1) {  // 연합이 있는 경우 인구 이동
		mean = (int)(population_sum / (float)country_cnt);

		while (!union_countries.empty()) {
			x = union_countries.front().first;
			y = union_countries.front().second;
			union_countries.pop();

			A[x][y] = mean;
		}
	}
	else
		union_countries.pop();

	return country_cnt;
}

int main() {
	scanf("%d %d %d", &N, &L, &R);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			scanf(" %d", &A[i][j]);
		}
	}

	is_moved = true;
	int day = 0;
	while (is_moved) { // 어느 한 곳도 인구이동이 없을 때 까지 반복
		is_moved = false;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (!check[i][j]) {
					if (bfs(i, j) > 1) {
						is_moved = true;
					}
				}
			}
		}

		if (is_moved) {  // 인구 이동이 있었던 경우
			// check 배열 초기화
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					check[i][j] = false;
				}
			}
			day++;
		}
		else {
			break;
		}
	}
	printf("%d", day);

	return 0;
}