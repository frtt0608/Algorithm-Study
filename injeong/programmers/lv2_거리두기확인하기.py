'''
대기실은 5개, 각 대기실은 5x5 크기
|r1 - r2| + |c1 - c2| > 2
단, 응시자가 앉아있는 자리 사이가 파티션으로 막혀 있을 경우에는 허용
P 응시자
O 빈테이블
X 칸막이
Question) 거리두기를 지키고 있으면 1을, 한 명이라도 지키지 않고 있으면 0 return
'''
# BFS 활용
# 거리가 작은 1부터 확인했을 때, 거리두기가 안 되어 있으면 바로 종료 가능하므로
# DFS보다는 BFS가 시간을 단축시킬 수 있다.
def solution(places):
    dy = [-1, 0, 1, 0]
    dx = [0, 1, 0, -1]

    def issafe(y, x): # 거리두기 확인
        q = []
        q.append([y,x,0])
        while q:
            y, x, dist = q.pop(0)
            visited[y][x] = True
            for i in range(4):
                ny = y + dy[i]
                nx = x + dx[i]
                dist += 1
                if 0<=ny<=4 and 0<=nx<=4 and not visited[ny][nx] and dist<=2:                    
                    if room[ny][nx] == 'P':
                        return False
                    elif room[ny][nx] == 'O':
                        q.append([ny, nx, dist])
                    else:
                        continue
        return True
    
    def findperson(maps): # 사람 있으면 바로 종료하도록 함수로 구현
        nonlocal visited
        for y in range(5):
            for x in range(5):
                if maps[y][x] == 'P': # 응시자 발견했을 때
                    visited = [[False for _ in range(5)] for _ in range(5)]
                    if not issafe(y, x): # 거리두기 안됐을 때
                        return True # == 사람 있다
        return False # == 사람 없다

    answer = []
    for room in places:

        visited = [] # 갱신
        flag = findperson(room)
        
        if not flag: # 결과 안 넣었다면 = 거리두기 잘 됐다는 뜻
            answer.append(1)
        else:
            answer.append(0)

    return answer

print(solution([["PXPXP", "OXXOX", "OPXPX", "OPXOX", "POXXP"], 
["POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"], 
["PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"], 
["OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"], 
["PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"]]), [1, 0, 1, 1, 1])