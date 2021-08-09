import sys
sys.stdin = open("input.txt", "r")
'''
NxN 땅
r,c = 행, 열 1부터 시작
모든 땅에는 양분이 5만큼 들어있다.
M개의 나무를 심었다. 한 격자에 여러개의 나무가 있을 수 있다.
봄 : 1)자신의 나이만큼 양분을 먹고 나이+=1 2)같은 땅에 여러 나무가 있다면, 어린 나무부터 양분을 먹는다. 3)양분이 부족해서 못 먹으면 즉시 죽는다.
여름 : 1)봄에 죽은 나무가 양분으로 변한다.-int(죽은 나무 나이//2)
가을 : 1)나무의 나이가 5의 배수면, 인접한 8개의 칸에 나이가 1인 나무가 생긴다. 단, 땅을 벗어나는 칸엔 안 생김
겨울 : 1)로봇이 땅에 양분을 추가한다. 각 칸에 추가되는 양분은 A배열 참고

=> K년이 지난 후 땅에 살아있는 나무의 개수를 구해라.
'''

if __name__ == '__main__':
    def printf(data):
        for d in data:
            print(d)
        print("==========================")

    for tc in range(1, int(input())+1):
        N, M, K = map(int, input().split())
        maps = [[5 for _ in range(N)] for _ in range(N)] #현재 양분
        A = [] #땅의 추가할 양분(겨울)
        for n in range(N):
            r = list(map(int, input().split()))
            A.append(r)
        tree_info = [[[] for _ in range(N)] for _ in range(N)] #심은 나무 정보, 나이
        for m in range(M):
            x, y, z = map(int, input().split())
            tree_info[x-1][y-1].append(z)

        for k in range(K): # K년
            # 봄
            tmp_maps = [[0 for _ in range(N)] for _ in range(N)] #여름에 추가될 양분
            for i in range(N):
                for j in range(N):
                    # 격자에 나무가 있다면,
                    if tree_info[i][j]:
                        a = sorted(tree_info[i][j])
                        next_tree = []
                        for r in range(len(a)):
                            if maps[i][j]>=a[r]: # 양분이 충분하다면
                                maps[i][j] -= a[r] # 나이만큼 양분 뺏기고
                                next_tree.append(a[r]+1) # 나무나이+1
                            else:
                                tmp_maps[i][j] += int(a[r]/2) # 죽은 나무만큼 양분 증가(임시)
                        tree_info[i][j] = next_tree
            # 여름
            for i in range(N):
                for j in range(N):
                    maps[i][j] += tmp_maps[i][j]
            # 가을, 겨울
            dr = [-1, -1, 0, 1, 1, 1, 0, -1]
            dc = [0, 1, 1, 1, 0, -1, -1, -1]
            for i in range(N):
                for j in range(N):
                    # 격자에 나무가 있다면,
                    a = tree_info[i][j]
                    if a:
                        for r in range(len(a)):
                            if a[r]%5 == 0 :
                                for d in range(8):
                                    ni = i+dr[d]
                                    nj = j+dc[d]
                                    if 0<=ni<N and 0<=nj<N :
                                        tree_info[ni][nj].append(1)
                    maps[i][j] += A[i][j]

        answer = 0
        for ti in range(N):
            for tj in range(N):
                answer += len(tree_info[ti][tj])

        print("#{} {}".format(tc, answer))

