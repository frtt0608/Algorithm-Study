'''
순서대로 정렬 후 return.
전체 승률이 높은 복서의 번호가 앞쪽으로 갑니다. 아직 다른 복서랑 붙어본 적이 없는 복서의 승률은 0%로 취급합니다.
승률이 동일한 복서의 번호들 중에서는 자신보다 몸무게가 무거운 복서를 이긴 횟수가 많은 복서의 번호가 앞쪽으로 갑니다.
자신보다 무거운 복서를 이긴 횟수까지 동일한 복서의 번호들 중에서는 자기 몸무게가 무거운 복서의 번호가 앞쪽으로 갑니다.
자기 몸무게까지 동일한 복서의 번호들 중에서는 작은 번호가 앞쪽으로 갑니다.

head2head[i] 는 i+1번 복서의 전적을 의미하며, head2head[i][j]는 i+1번 복서와 j+1번 복서의 매치 결과를 의미합니다.
'N' (None)은 두 복서가 아직 붙어본 적이 없음을 의미합니다.
'W' (Win)는 i+1번 복서가 j+1번 복서를 이겼음을 의미합니다.
'L' (Lose)는 i+1번 복사가 j+1번 복서에게 졌음을 의미합니다.
=> i 기준 j와의 결과 = 대칭행렬

선수 번호	vs 1번	vs 2번	vs 3번	vs 4번	승률	자기보다 무거운 복서를 이긴 횟수	몸무게
1번	-	패배	승리	패배	33.33%	1회	50kg
2번	승리	-	패배	패배	33.33%	0회	82kg
3번	패배	승리	-	승리	66.66%	2회	75kg
4번	승리	승리	패배	-	66.66%	0회	120kg

'''
if __name__ == '__main__':
    import collections
    def solution(weights, head2head):
        # 표를 기준으로 만들어보자.
        n = len(weights) # 선수 수
        tables = collections.defaultdict(int)

        def cal(idx, data):
            # data : 승패결과
            cnt, win, win_w = 0, 0, 0
            for i in range(len(data)):
                if data[i] != "N": 
                    cnt += 1
                    if data[i] == "W" :
                        win += 1
                        if weights[idx]<weights[i]:
                            win_w += 1
            if cnt:
                return [(win/cnt), win_w]
            else:
                return [0, 0]
            

        for i in range(1, n+1):
            res = cal(i-1, head2head[i-1])
            tables[i] = [res[0], res[1], weights[i-1], i] # 승률>몸무게 기준 이긴횟수>내 몸무게>작은 번호
        tables = sorted(tables.values(), key=lambda x:(x[0], x[1], x[2], -x[3]), reverse=True)

        answer = [x[3] for x in tables]
        return answer

    print(solution([50,82,75,120], ["NLWL","WNLL","LWNW","WWLN"]), [3,4,1,2])
    print(solution([145,92,86], ["NLW","WNL","LWN"]), [2,3,1])
    print(solution([60,70,60], ["NNN","NNN","NNN"]), [2,1,3])