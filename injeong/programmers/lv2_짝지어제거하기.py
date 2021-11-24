def solution(s):
    going = True
    while going:
        going = False
        lists = list(set(s))
        for word in lists:
            w = word*2
            tmp_s = s.replace(w, '')
            if s != tmp_s: # 변화가 있으면
                s = tmp_s
                going = True
    if not s:
        return 1
    else:
        return 0

print(solution("baabaa"), 1)
print(solution("cdcd"), 0)