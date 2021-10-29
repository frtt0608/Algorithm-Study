'''
주어진 도형의 바깥쪽 테두리를 통해 이동한다.
- 중간이 비어 있을 수 있다. (내부 둘레는 사용 안함)
- 겹쳐진 도형은 없다.
- x축 좌표, y축 좌표가 같은 도형은 없다. (다 어긋나있다.)
Input
지형을 나타내는 직사각형이 담긴 2차원 배열 rectangle
- [좌측 하단 x, 좌측 하단 y, 우측 상단 x, 우측 상단 y] 
초기 캐릭터의 위치 characterX, characterY
아이템의 위치 itemX, itemY
Output
캐릭터가 아이템을 줍기 위해 이동해야 하는 가장 짧은 거리
Sol)
- 
'''
def printf(data, x1, x2, y1, y2):
    print(x1, y1, x2, y2)
    for i in range(y2-1, y1-1, -1):
        print(data[i][x1:x2+1])
    print("================")
    return
def solution(rectangle, characterX, characterY, itemX, itemY):
    maps = [[0 for _ in range(50)] for _ in range(50)]
    min_x, max_x, min_y, max_y = 50, 0, 50, 0
    t = 1
    for r in rectangle:
        x1, y1, x2, y2 = r
        min_x = min(x1, x2, min_x)
        max_x = max(x1, x2, max_x)
        min_y = min(y1, y2, min_y)
        max_y = max(y1, y2, max_y)
        for y in range(y1, y2):
            for x in range(x1, x2):
                maps[y][x] = t
        t += 1
    printf(maps, min_x, max_x, min_y, max_y)

    # 둘레만 남기고 없애기
    range_x = max_x - min_x +1
    range_y = max_y - min_y +1
    dy = [-1, -1, 0, 1, 1, 1, 0, -1]
    dx = [0, 1, 1, 1, 0, -1, -1, -1]
    edge_maps = [[0 for _ in range(range_x)] for _ in range(range_y)]
    for i in range(range_y):
        for j in range(range_x):
            if maps[i][j]!=0:
                cnt = 0
                for k in range(8):
                    ny = i + dy[k]
                    nx = j + dx[k]
                    if ny<0 or ny>=range_y or nx<0 or nx>=range_x :
                        continue
                    elif maps[ny][nx]!=0:
                        cnt += 1
                if cnt != 8:
                    edge_maps[i][j] = maps[i][j]
    printf(edge_maps, min_x, max_x, min_y, max_y)

    # 최단거리 구하기
    
    answer = 0
    return answer

print(solution([[1,1,7,4],[3,2,5,5],[4,3,6,9],[2,6,8,8]],1,3,7,8), 17)
# print(solution([[1,1,8,4],[2,2,4,9],[3,6,9,8],[6,3,7,7]],9,7,6,1),11)
print(solution([[1,1,5,7]],1,1,4,7),9)
# print(solution([[2,1,7,5],[6,4,10,10]],3,1,7,10),15)
# print(solution([[2,2,5,5],[1,3,6,4],[3,1,4,6]],1,4,6,3),10)