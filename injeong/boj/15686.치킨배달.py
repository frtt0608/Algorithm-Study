'''
NxN 도시가 있다. 각 칸은 빈 칸, 집, 치킨집 중 하나이다.
- 치킨 거리 : 집과 가가운 치킨집 사이의 거리
- 도시의 치킨 거리 : 모든 집의 치킨 거리의 합
도시에 있는 치킨집 중 최대 M개를 고르고, 나머지는 모두 폐업.
어떻게 고르면 도시의 치킨 거리가 가장 작게 될지 구하는 프로그램을 작성해라.
'''
# 1. M개 고르기
# 2. 도시의 치킨 거리 구하기
# 3. 비교

import sys
sys.stdin = open("input.txt", "r")

from itertools import combinations
if __name__ == '__main__' :
    for tc in range(1, int(input())+1) :
        N, M = map(int, input().split())
        maps = []
        chicken = []
        home = []
        for n in range(N):
            x = list(map(int, input().split()))
            maps.append(x)
            for i in range(N):
                if x[i] == 2 : chicken.append((n,i))
                elif x[i] == 1 : home.append((n,i))
        
        def calc(a, b):
            x1, y1 = a
            x2, y2 = b
            return abs(x1-x2)+abs(y1-y2)



        # 0(빈칸) 1(집) 2(치킨집)
        # print('h', home)
        # print('c', chicken)
        lh = len(home)
        answer = 2*N*lh+1

        for c in combinations(chicken, M):
            tmp_chicken = c
            tmp_answer = 0
            lc = len(tmp_chicken)
            for i in range(lh):
                min_value = 2*N+1
                for j in range(lc):
                    min_value = min(calc(home[i], tmp_chicken[j]), min_value)
                tmp_answer += min_value
            answer = min(answer, tmp_answer)
        print(answer)