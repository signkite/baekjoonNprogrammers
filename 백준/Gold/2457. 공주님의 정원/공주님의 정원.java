import java.io.*;
import java.util.*;

class Date implements Comparable<Date> {
	int month;
	int day;
	
	Date(int month, int day) {
		this.month = month;
		this.day = day;
	}
	
	@Override
	public int compareTo(Date another) {
		if (this.month == another.month)
			return this.day - another.day;
		return this.month - another.month;
	}
}

class Project implements Comparable<Project> {
	Date start;
	Date end;
	
	public Project(int startMonth, int startDay, int endMonth, int endDay) {
		this.start = new Date(startMonth, startDay);
		this.end = new Date(endMonth, endDay);
	}
	
	@Override
	// 시작 날짜 순으로 정렬
	// 시작 날짜가 같다면 끝 날짜 순으로 정렬
	public int compareTo(Project another) {
		if (start.compareTo(another.start) == 0) {
			return end.compareTo(another.end);
		}
		return start.compareTo(another.start);
	}
}

public class Main {
	static int N;
	static Project[] projects;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		projects = new Project[N];
		
		for (int i = 0; i < N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int sm = Integer.parseInt(st.nextToken());
			int sd = Integer.parseInt(st.nextToken());
			int em = Integer.parseInt(st.nextToken());
			int ed = Integer.parseInt(st.nextToken());
			
			projects[i] = new Project(sm, sd, em, ed);
		}
		
		// 프로젝트 시작일 순으로 정렬, 시작일이 같다면 끝날짜 순으로 정렬
		Arrays.sort(projects);
		
		int i = 0;
		int count = 0;
		Date curEnd = new Date(3, 1);
		Date finalDate = new Date(11, 30);
		boolean isPossible = false;
		
		while (i < N) {
			
			int flag = 0;

			// 현재 프로젝트의 종료시점부터 바로 시작할 수 있는 모든 프로젝트를 조사하여
			// 가장 종료점이 나중인 프로젝트를 선택
			Date maxEnd = new Date(curEnd.month, curEnd.day);
			while (i < N && curEnd.compareTo(projects[i].start) >= 0) {
				if (maxEnd.compareTo(projects[i].end) < 0) {
					maxEnd = projects[i].end;
				}
				flag++;
				i++;
			}
			
			// 만족하는 프로젝트가 없다면 불가능
			if (flag == 0) {
				break;
			}
			
			curEnd = maxEnd;
			count++;
			
			// 종료점이 11월 30일 이후인지 체크
			if (finalDate.compareTo(curEnd) < 0) {
				isPossible = true;
				break;
			}
		}
		
		System.out.println(isPossible ? count : 0);
	}
}