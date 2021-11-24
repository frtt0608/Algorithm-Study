def solution(s):
    stack = []
    for word in s:
        if not stack or stack[-1]!=word:
            stack.append(word)
        elif stack[-1]==word:
            stack.pop(-1)

    if not stack:
        return 1
    else:
        return 0

print(solution("baabaa"), 1)
print(solution("cdcd"), 0)
print(solution("daddccada"), 0)