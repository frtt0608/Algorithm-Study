import sys
sys.stdin = open("input3.txt", "r")
'''
NxN 교실에 (1,1)~(N,N)자리가 있다.
- 학생이 좋아하는 4명 조사
- |r1 - r2| + |c1 - c2| = 1을 만족하는 두 칸이 (r1, c1)과 (r2, c2)를 인접하다고 한다
1. 자리배치
    1) 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
    2) 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
    3) 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
2. 만족도
    - 인접한 칸에 앉은 학생 수에 따라,
    - 값이 0이면 학생의 만족도는 0, 1이면 1, 2이면 10, 3이면 100, 4이면 1000
=> 만족도의 총 합을 구하자
'''
# 시뮬레이션
if __name__ == '__main__':
    def printf(data):
        for d in data:
            print(d)
        print("------------------")

    for tc in range(1, int(input())+1):
        # 1) 어떻게 받을까? 어떻게 문제를 풀까?
        N = int(input())
        maps = [[0 for _ in range(N)] for _ in range(N)]
        orders = {}
        for i in range(N*N):
            x = list(map(int, input().split()))
            orders[x[0]] = x[1:]

        # 2) 인접한 칸 = 동서남북
        dy = [-1, 0, 1, 0]
        dx = [0, 1, 0, -1]
        def find(r, c, n):
            blank, likes = 0, 0
            for i in range(4):
                nr = r + dy[i]
                nc = c + dx[i]
                if 0<=nr<N and 0<=nc<N :
                    if maps[nr][nc] == 0 : 
                        blank += 1
                    elif maps[nr][nc] in orders[n]:
                        likes += 1
            return [blank, likes] # 비어있는 칸, 좋아하는 학생 수

        result = {}
        for i in range(N):
            for j in range(N):
                k = (i, j)
                result[k] = [] # ischeck(들어갈 수 있는지 여부), likes, blank, row, col

        for n, v in orders.items():
            like = v
            for y in range(N):
                for x in range(N):
                    if maps[y][x] != 0 :
                        result[(y,x)] = [0, 0, 0, y, x] # 초기화
                        continue
                    blank, likes = find(y, x, n)
                    result[(y,x)] = [1, likes, blank, y, x]
            now = sorted(result.values(), key=lambda x:(x[0], x[1], x[2], -x[3], -x[4]), reverse=True)[0]
            maps[now[3]][now[4]] = n

        # 3) 만족도
        satisfaction = 0
        for r in range(N):
            for c in range(N):
                cnt = 0
                n = maps[r][c]
                for i in range(4):
                    nr = r + dy[i]
                    nc = c + dx[i]
                    if 0<=nr<N and 0<=nc<N and maps[nr][nc] in orders[n]:
                        cnt += 1
                if cnt != 0:
                    satisfaction += 10**(cnt-1)
        print(satisfaction)
        # print("#{} {}".format(tc, satisfaction))