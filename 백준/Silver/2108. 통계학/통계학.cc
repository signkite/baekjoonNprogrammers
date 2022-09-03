#include<iostream>
#include<string>
#include<cmath>
#include<algorithm>
using namespace std;

int N;
int num_cnt[8001];  // 수의 개수 num_cnt[x] == N개의 수 중 x - 4000의 개수 (최빈값 구할 때 사용)
int nums[500000];
int sum;

int main(){
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N;
	int num;
	for (int i = 0; i < N; ++i) {
		cin >> num;
		num_cnt[num + 4000] += 1;
		nums[i] = num;
		sum += num;
	}

	sort(nums, nums + N);

	// 산술평균
	double mean = sum / (double)N;
	printf("%d\n", (int)round(mean));

	// 중앙값
	printf("%d\n", nums[N / 2]);

	// 최빈값
	int max_idx = 0;
	int max_cnt = 0;
	int freq[500000]{};
	int freq_idx = 0;
	for (int i = 0; i < 8001; ++i) {
		if (num_cnt[i] > max_cnt) {
			max_cnt = num_cnt[i];
			max_idx = i;
			freq_idx = 0;
			freq[0] = max_idx - 4000;
		}
		else if (num_cnt[i] == max_cnt) {
			freq[++freq_idx] = i - 4000;
		}
	}

	sort(freq, freq + freq_idx + 1);
	if (freq_idx < 1)
		printf("%d\n", freq[0]);
	else
		printf("%d\n", freq[1]);

	// 범위
	printf("%d\n", nums[N - 1] - nums[0]);

	return 0;
}