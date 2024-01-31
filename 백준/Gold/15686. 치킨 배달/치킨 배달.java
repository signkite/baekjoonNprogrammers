import java.io.*;
import java.util.*;

// 좌표 값을 저장할 클래스
class Coord {
	int x;
	int y;
	Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int N, M;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static List<Coord> chickens = new ArrayList<>();  // 치킨집들의 좌표
	static List<Coord> houses = new ArrayList<>();  // 집들의 좌표
	static int minCityChickenDist = Integer.MAX_VALUE; 
	
	static List<Coord> selectedChickens = new LinkedList<>();  // M개의 조합으로 선택된 치킨집 좌표
	static void combination(int i, int startIdx) {
		if (i == M) {
			// minCityChickenDist 갱신
			minCityChickenDist = Math.min(minCityChickenDist, getCityChickenDist());
			return;
		}
		
		for (int j = startIdx, size = chickens.size(); j < size; ++j) {
			selectedChickens.add(chickens.get(j));
			combination(i + 1, j + 1);
			selectedChickens.remove(i);
		}
	}
	
	// selectedChickens 를 지도에 적용해 도시의 치킨거리를 구해 반환하는 메서드
	static int getCityChickenDist() {
		int curCityChickenDist = 0;
		
		for(Coord house: houses) {
			curCityChickenDist += getChickenDist(house);
		}
		
		return curCityChickenDist;
	}
	
	// x, y 에 있는 집의 치킨거리를 구하는 메서드
	static int getChickenDist(Coord house) {
		int minDist = 100;
		
		for (Coord chicken: selectedChickens) {
			int dist = Math.abs(house.x - chicken.x) + Math.abs(house.y - chicken.y);
			minDist = Math.min(dist, minDist);
		}
		
		return minDist;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// N, M 입력 및 변수 초기화
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 도시 입력 받으며 집, 치킨집 좌표도 동시에 저장
		for (int x = 0; x < N; ++x) {
			st = new StringTokenizer(br.readLine());
			for (int y = 0; y < N; ++y) {
				int input = Integer.parseInt(st.nextToken());
				
				if (input == 1) {
					houses.add(new Coord(x, y));
				}
				else if (input == 2) {
					chickens.add(new Coord(x, y));
				}
			}
		}
		
		// 최소 치킨거리 계산 및 출력
		combination(0, 0);
		System.out.println(minCityChickenDist);
	}
}
