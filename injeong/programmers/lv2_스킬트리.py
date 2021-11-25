'''
선행 스킬 순서 skill과 유저들이 만든 스킬트리를 담은 배열 skill_trees 있을 때
가능한 스킬트리 개수를 return
'''
def solution(skill, skill_trees):
    db = {}
    for idx, s in enumerate(skill):
        db[s] = idx

    answer = 0
    for skills in skill_trees:
        flag = True
        check = [False]*len(skill)
        for s in skills:
            if db.get(s)!=None:
                if db[s]!=0 and not check[db[s]-1]:
                    flag = False
                    break
                else:
                    check[db[s]] = True
        if flag:
            answer += 1
    return answer

print(solution("CBD",["BACDE", "CBADF", "AECB", "BDA"]), 2)
print(solution("BAD",["BACDE", "CBADF", "AECB", "BDA"]), 2)