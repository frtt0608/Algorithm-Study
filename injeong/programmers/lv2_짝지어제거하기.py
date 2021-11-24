def solution(s):
    going = True
    while going:
        going = False
        lists = {}
        for word in s:
            if lists.get(word):
                lists[word] += 1
            else:
                lists[word] = 1
        sort_lists = sorted(lists.items(), key=lambda x: x[1], reverse=True)
        for word in sort_lists:
            w = word[0]*2
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