import sys
sys.stdin = open("input.txt", "r")

'''
드래곤 커브
    (s)
    |
    (e)
(s)-(e) ->시계방향90도-> 두개 붙임

드래곤 커브 N개
네 꼭지점이 모두 드래곤 커브의 일부인 정사각형의 개수를 구해라.
'''
if __name__ == '__main__':
    def printf(data):
        for d in data:
            print(d)
        print("------------------")

    for tc in range(1, int(input())+1):
        # 우, 상, 좌, 하
        dy = [0, -1, 0, 1]
        dx = [1, 0, -1, 0]

        N = int(input())
        curves = []
        dirs = [[] for _ in range(N)] # 세대 이동 초기화
        for n in range(N):
            x, y, d, g = map(int, input().split())
            curves.append([x,y,d,g]) # 커브에 정보 삽입
            # 0세대~g세대까지 이동 값 삽입
            for i in range(g+1):
                if i == 0 :
                    dirs[n].append(d)
                else:
                    # k-1세대의 기존배치와 역순배치(+1)를 더해줌
                    rev = [(x+1)%4 for x in list(reversed(dirs[n]))]
                    dirs[n].extend(rev)

        maps = [[False]*101 for _ in range(101)] # 지도 초기화
        for i in range(N):
            x, y, d, g = curves[i] # 시작점
            maps[y][x] = True
            for j in dirs[i]: # 가야 할 방향
                ny = y + dy[j]
                nx = x + dx[j]
                if 0<=ny<101 and 0<=nx<101:
                    maps[ny][nx] = True # 지나간 자리 체크
                    y, x = ny, nx # 끝점을 시작점으로 변경(즉, 포인터 이동)

        # 네 꼭지점을 거쳐갔는지 확인
        answer = 0
        for y in range(100):
            for x in range(100):
                if maps[y][x]==True and maps[y+1][x]==True and maps[y][x+1]==True and maps[y+1][x+1]==True:
                    answer += 1
                    
        print(answer)