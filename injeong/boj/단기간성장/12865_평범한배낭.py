# Gold 5
'''
N개의 물건:무게W, 가치V
물건을 배낭에 넣으면 V만큼 즐길 수 있다
최대 K 무게만큼 넣을 수 있다
Question) 최대한 즐거운 여행을 위한 배낭에 넣을 수 있는 물건들의 가치의 최댓값
Sol)
    쪼갤 수 있는 경우 : 분할가능 배낭문제(Fractional Knapsack Problem)
    (해당) 쪼갤 수 없는 경우 : 0-1 배낭문제(0-1 Knapsack Problem)
'''
# 0-1배낭문제 => DP이용
# 가방 크기에 따른 최대 이윤을 구하기
# 참고) https://huiyu.tistory.com/entry/DP-01-Knapsack%EB%B0%B0%EB%82%AD-%EB%AC%B8%EC%A0%9C

import sys
sys.stdin = open("input.txt", "r")

if __name__=='__main__':
    N, K = map(int, input().split(' '))
    objects = []
    for i in range(N):
        objects.append(list(map(int, input().split(' '))))
    # objects = sorted(objects)

    # 행:물건, 열:K까지의 무게
    results = [[0 for _ in range(K+1)] for _ in range(N)]
    for y in range(N):
        w, v = objects[y]
        # print(w, v)
        for x in range(1, K+1): # x:지금 가방무게
            if x < w: # 현재 가방 무게가 물건보다 가벼우면, 넣을 수 있었던 최댓값을 그대로 가져온다.
                results[y][x] = results[y-1][x]
            else:
                results[y][x] = max(v+results[y-1][x-w], results[y-1][x])
            # max(현재 물건의 무게일 때의 가치+(지금 가방 무게-현재 무게)했을 때 남는 무게의 최댓값(=전 행의 그 무게))
            # 현재 가방 무게의 최댓값과 비교 
            # 즉, 현재 가방 무게에서 넣을 수 있는 최댓값을 비교하기
            # K=7이면, w=3인 물건을 넣을 때, 남은 무게 4를 넣을 때의 최댓값을 넣는 격
    # for r in results:
    #     print(r)
    print(results[N-1][K])
    
