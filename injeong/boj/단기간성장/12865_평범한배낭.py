# Gold 5
'''
N개의 물건:무게W, 가치V
물건을 배낭에 넣으면 V만큼 즐길 수 있다
최대 K 무게만큼 넣을 수 있다
Question) 최대한 즐거운 여행을 위한 배낭에 넣을 수 있는 물건들의 가치의 최댓값
'''
import sys
sys.stdin = open("input.txt", "r")

if __name__=='__main__':
    # 0. 어떤 방법으로 시작할 지 고민
    # - 조건이 있는 조합 : K보다 작은 무게 내에서 고르기
    # - 고른 후 V의 합이 최대인 것
    N, K = map(int, input().split(' '))
    objects = []
    for i in range(N):
        objects.append(list(map(int, input().split(' '))))
    objects = sorted(objects)
    # print(objects)

    result = 0
    # 1. 1개~N개까지 고르는 조합
    def comb(r, curr, total_weight, total_value):
        global result
        if total_weight > K:
            return
        if r==0:
            # print(visited)
            if total_value >= result:
                result = total_value
            return
        for i in range(curr, N):
            if i not in visited:
                visited.append(i)
                weight = objects[i][0]
                value = objects[i][1]
                comb(r-1, i, total_weight+weight, total_value+value)
                visited.pop(-1)
        return
    for i in range(1, N+1):
        visited = []
        total_weight = 0
        total_value = 0
        comb(i, 0, 0, 0)
    print(result)
