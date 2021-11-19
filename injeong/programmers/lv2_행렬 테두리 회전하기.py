'''
(x1, y1, x2, y2) 회전 ==
x1 행 y1 열부터 x2 행 y2 열까지의 영역에 해당하는 직사각형에서 
테두리에 있는 숫자들을 한 칸씩 시계방향으로 회전합니다.
Question) 각 회전들을 배열에 적용한 뒤, 그 회전에 의해 위치가 바뀐 숫자들 중 
가장 작은 숫자들을 순서대로 배열에 담아 return
'''

def solution(rows, columns, queries):
    # 행의 개수, 열의 개수, 회전목록
    # queries만큼 반복
        # 회전 시키면서, 가장 작은 숫자 갱신
        # 마지막에 answer에 append
    dy = [0, 1, 0, -1]
    dx = [1, 0, -1, 0]
    i = 1

    maps = []
    for y in range(rows):
        tmp_map = [i+x for x in range(columns)]
        maps.append(tmp_map)
        i += columns
    # for m in maps:
    #     print(m)

    def rotation(y1, x1, y2, x2):
        result = rows*columns # 최솟값 저장
        y, x = y1, x1
        now_val = maps[y][x]
        i = 0
        while True:
            if i == 4:
                break
            ny = y + dy[i]
            nx = x + dx[i]
            if y1<=ny<=y2 and x1<=nx<=x2:
                next_val = maps[ny][nx] # next_point 값 임시저장
                maps[ny][nx] = now_val # 현재 값 다음 자리에 넣기
                y, x, now_val = ny, nx, next_val
                if result > now_val:
                    result = now_val
            else:
                i += 1
        return result

    answer = []
    for q in queries:
        a, b, c, d = [o-1 for o in q]
        min_value = rotation(a, b, c, d)
        answer.append(min_value)
    return answer

print(solution(6,6,[[2,2,5,4],[3,3,6,6],[5,1,6,3]]), [8, 10, 25])
print(solution(3,3,[[1,1,2,2],[1,2,2,3],[2,1,3,2],[2,2,3,3]]), [1, 1, 5, 3])
print(solution(100,97,[[1,1,100,97]]), [1])