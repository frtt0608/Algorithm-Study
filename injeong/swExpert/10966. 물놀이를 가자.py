import sys
sys.stdin = open("input.txt", "r")

if __name__ == '__main__' :
    def printf(data):
        for d in data:
            print(d)
        
    for tc in range(1, int(input())+1):
        N, M = map(int, input().split())
        maps = []
        waters = []
        for n in range(N):
            x = list(input())
            maps.append(x)
            for m in range(M):
                if x[m] == 'W' :
                    waters.append([n, m])
        # printf(maps)
        # print(waters)
        dy = [-1, 0, 1, 0]
        dx = [0, 1, 0, -1]
        chk = [[N*M for _ in range(M)] for _ in range(N)]
        for w in waters:
            q = [w]
            visited = [[False for _ in range(M)] for _ in range(N)]
            chk[w[0]][w[1]] = 0
            while q:
                y, x = q.pop(0)
                visited[y][x] = True
                for d in range(4):
                    ny = y + dy[d]
                    nx = x + dx[d]
                    if 0<=ny<N and 0<=nx<M and maps[ny][nx] == 'L' and visited[ny][nx] == False:
                        chk[ny][nx] = min(chk[ny][nx], chk[y][x]+1)
                        q.append([ny, nx])
        # print(chk)

        answer = 0
        for n in range(N):
            for m in range(M):
                if maps[n][m] == 'L':
                    answer += chk[n][m]
        print("#{} {}".format(tc, answer))