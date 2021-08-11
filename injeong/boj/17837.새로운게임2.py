import sys
sys.stdin = open("input2.txt", "r")
'''
NxN 체스판, K개의 말
하나의 말 위에 다른 말 올릴 수 있다.
각 칸은 흰, 빨, 파 중 1개
1~K번의 말이 올라가 있고, 이동 방향도 정해져 있다.
turn1 - 모든 말을 순서대로 이동시키는 것. 한 말이 이동할 때 올려져 있는 말도 함께 이동
        칸에 따라서 말의 이동이 다르다. 턴이 진행 중 말이 4개 이상 쌓이면 게임 끝.
    Next 칸이,
        흰색(0) : 이동하고 말이 있을 경우, 가장 위에 해당 말을 올림
               이동할 때 말 위의 모든 말 같이 이동
        빨(1) : 이동 후에 해당 말과 그 위에 있는 모든 말의 순서를 반대로 변경
             A-B-C -> C-B-A
             A-B-C/D-E ->D-E-C-B-A
        파(2)/체스판 벗어날 때 : 말의 이동 방향을 반대로 하고 이동. 방향 바꿨는데 또 파란색이면 이동 안함
게임이 종료되는 TURN의 번호를 구하자.
그 값이 1,000보다 크거나 절대로 게임이 종료되지 않는 경우에는 -1을 출력한다.
'''

from itertools import combinations
if __name__ == '__main__' :
    def printf(data):
        for d in data:
            print(d)
        print("================================")

    for tc in range(1, int(input())+1) :
        N, K = map(int, input().split())
        maps = []
        for i in range(N):
            x = list(map(int, input().split()))
            maps.append(x)
        maps_pin = [[[] for _ in range(N)] for _ in range(N)] # 말 위치만
        pins = []
        for i in range(K):
            x, y, d = map(int, input().split())
            maps_pin[x-1][y-1].append((i+1)) # 말 위치 기록
            pins.append([x-1, y-1, d]) # 말 정보 기록(위치, 방향)



        # (오, 왼, 위, 아래 = 1, 2, 3, 4)
        dx = [0, 0, -1, 1] # 행
        dy = [1, -1, 0, 0] # 열
        change_d = [2, 1, 4, 3] # 바꿔야 할 방향

        def move(idx, pin_data):
            # 핀 번호, 핀 방향, 이동할 위치 x,y
            x, y, d = pin_data
            nx = x + dx[d-1]
            ny = y + dy[d-1]
            if nx<0 or nx>=N or ny<0 or ny>=N or maps[nx][ny] == 2 :
                d = change_d[d-1]
                pins[idx-1][2] = d # 바뀐 방향 입력
                nx = x + dx[d-1]
                ny = y + dy[d-1]
            if (0<=nx<N and 0<=ny<N) and maps[nx][ny] != 2:
                j = maps_pin[x][y].index(idx)
                move_pin = maps_pin[x][y][j:]
                maps_pin[x][y] = maps_pin[x][y][:j]
                if maps[nx][ny] == 0 :
                    maps_pin[nx][ny].extend(move_pin)
                elif maps[nx][ny] == 1 :
                    reverse_move_pin = [move_pin[r] for r in range(len(move_pin)-1, -1, -1)]
                    maps_pin[nx][ny].extend(reverse_move_pin)
                for k in move_pin:
                    pins[k-1][0] = nx
                    pins[k-1][1] = ny
            return

        printf(maps)
        printf(maps_pin)
        printf(pins)
        print("시작")

        def finish():
            for p in range(N):
                for q in range(N):
                    if len(maps_pin[p][q]) >= 4 :
                        return True
            return False
        turn = 0
        while True:
            if finish():
                print(turn)
                break
            if turn >= 1000 :
                print(-1)
                break
            for i in range(K):
                pin_data = pins[i]
                move(i+1, pin_data)
            turn += 1
            #printf(maps)
            #printf(maps_pin)
            #printf(pins)
            #print("***turn 종료***")


        print(tc)