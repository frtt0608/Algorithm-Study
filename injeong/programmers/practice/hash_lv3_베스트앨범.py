'''
장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다.
- 노래 수록 기준
속한 노래가 많이 재생된 장르를 먼저 수록합니다.
장르 내에서 많이 재생된 노래를 먼저 수록합니다.
장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
-> 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return
'''
# 런타임에러 or 실패
def solution(genres, plays):
    dicts = {}
    for i, x in enumerate(genres):
        if x not in dicts.keys(): 
            dicts[x] = [(plays[i], i)]
        else:
            dicts[x].append((plays[i],i))

    sort_lists = sorted(dicts.items(), key=lambda x : (-max(x[1][0])))
    
    answer = []
    for item in sort_lists:
        k, v = item[0], item[1]
        v.sort(key=lambda x: (-x[0], x[1]))
        answer.append(v[0][1])
        answer.append(v[1][1])

    return answer

print(solution(["classic", "pop", "classic", "classic", "pop"],
[500, 600, 800, 800, 2500]), [4, 1, 2, 3])