'''
I 숫자	큐에 주어진 숫자를 삽입합니다.
D 1	큐에서 최댓값을 삭제합니다.
D -1	큐에서 최솟값을 삭제합니다.
* 빈 큐에 데이터를 삭제하라는 연산이 주어질 경우, 해당 연산은 무시
큐가 비어있으면 [0,0] 비어있지 않으면 [최댓값, 최솟값]을 return
def solution(operations):
    q = []
    for op in operations:
        io, num = op.split(' ')
        print(io, int(num))
        num = int(num)
        if io=='I':
            q.append(num)
        elif q:
            if num > 0 :
                q.pop(q.index(max(q)))
            elif num < 0:
                q.pop(q.index(min(q)))
        print(q)
    if not q:
        return [0,0]
    else:
        return [max(q), min(q)]

print(solution(["I 16","D 1"]),[0,0])
print(solution(["I 7","I 5","I -5","D -1"]),[7,5])
'''




# 권투 경기 -> 순위 매기기
# 시간초과....
def solution(n, results):
    # [A, B] = A가 B에게 항상 이긴다 = B가 A에게 항상 진다
    # 행=내가 이기는 선수 체크, 열=나를 이기는 선수 체크
    maps = [[0 for _ in range(n)] for _ in range(n)]
    cnt = {}
    for result in results:
        winner = result[0]-1
        loser = result[1]-1
        maps[winner][loser] = 1 # 이긴거
        maps[loser][winner] = -1 # 진거
        if not cnt.get(winner):
            cnt[winner] = 1
        else:
            cnt[winner] += 1
        if not cnt.get(loser):
            cnt[loser] = 1
        else:
            cnt[loser] += 1

    # 내가 이긴 선수, 진 선수 구별
    def fight(me, maps):
        win = []
        lose = []
        for i, v in enumerate(maps[me]):
            if v==-1:
                lose.append(i)
            elif v==1:
                win.append(i)
        return [win, lose]

    # 대결 횟수가 많은 순서로 정렬
    cnt = sorted(cnt.items(), key=lambda x:x[1], reverse=True)

    for v in cnt:
        i = v[0]
        win, lose = fight(i, maps)
        print(win, lose)
        for w in win: # 내가 이김
            for l in lose: # 내가 짐
                # 내가 진 선수는 내가 이긴 선수에게 무조건 짐 = w>l
                maps[w][l] = -1
                maps[l][w] = 1
        
    for m in maps:
        print(m)
    print("=============")


    # 가로 중 0이 1개만 존재해야 함
    answer = 0
    for y in range(n):
        zero = 0
        for x in range(n):
            if maps[y][x] == 0 :
                zero += 1
        if zero == 1 :
            answer += 1
    return answer

print(solution(5, [[4, 3], [4, 2], [3, 2], [1, 2], [2, 5]]),2)


# 모든 선수끼리의 경기 결과가 주어진다면,
'''
boxers = [[0,0] for _ in range(n)] # 항상 승리하는 수, 패배하는 수
for result in results:
    a, b = result
    boxers[a-1][0] += 1
    boxers[b-1][1] += 1
print(boxers)
'''
