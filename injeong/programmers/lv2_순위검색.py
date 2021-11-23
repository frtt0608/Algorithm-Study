'''
언어, 직군, 경력, 소울푸드 반드시 하나 선택
Question) [조건]을 만족하는 사람 중 코딩테스트 점수를 X점 이상 받은 사람은 모두 몇 명인가?
'''
# python에서는 해시테이블을 dict 형태로 제공한다.
# 즉, key를 이용해서 value를 바로 가져올 수 있다.
def solution(info, query):
    db = {}
    for options in info:
        a, b, c, d, score = options.split(' ')
        for aa in [a, '-']:
            for bb in [b, '-']:
                for cc in [c, '-']:
                    for dd in [d, '-']:
                        key = aa+bb+cc+dd
                        if db.get(key)==None:
                            db[key] = []
                        db[key].append(int(score))
    print(db)

    answer = []
    for x in query:
        x = x.replace('and ', '').split(' ')
        q = x[:-1]
        key = ''.join(q)
        score = int(x[-1])
        print(key)
        if db.get(key)==None:
            answer.append(0)
            break
        lists = sorted(db[key])
        mid = len(lists)//2
        while True:
            if lists[mid]>=score:
                if mid>0 and lists[mid-1]==score:
                    mid -= 1
                    continue
                else:
                    break
            if lists[mid] > score:
                mid //=2
            else:
                mid += mid//2
        answer.append(len(lists[mid:]))

    return answer

print(solution(["java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"],
["java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"]), [1,1,1,1,2,4])