'''
문자열을 단위로 압축했을 때 가장 짧은 길이로 압축하는 방법
문자열은 제일 앞부터 정해진 길이만큼 잘라야 한다.
Ex) xababcdcdababcdcd -> x부터 잘라야 하므로, 반복되지 않음.
Ex) abcabcdede -> (2개)abcabc2de (3개)2abcdede -> 3개로 압축
'''

'''
Solution)

'''
def solution(s):
    answer = 10**10
    n = len(s)
    for i in range(1, n+1):
        tmp_s = []
        result = ''
        for j in range(0, n, i):
            w = s[j:j+i]
            if not tmp_s:
                tmp_s.append([w, 1])
            elif w != tmp_s[-1][0]:
                if tmp_s[-1][1] != 1:
                    result += str(tmp_s[-1][1])+tmp_s[-1][0]
                else:
                    result += tmp_s[-1][0]
                tmp_s.append([w, 1])
            else:
                tmp_s[-1][1] += 1
        if tmp_s[-1][1] != 1:
            result += str(tmp_s[-1][1])+tmp_s[-1][0]
        else:
            result += tmp_s[-1][0]
        # print(tmp_s)
        # print(result)
        answer = min(answer, len(result))
    return answer

print(solution("aabbaccc"),7) # 2a2ba3c
print(solution("abcabcdede"),8) # 2abcdede
print(solution("xababcdcdababcdcd"),17) # xababcdcdababcdcd
print(solution("ababcdcdababcdcd"),9)
print(solution("abcabcabcabcdededededede"),14)


# def solution(s):
#     max_len = 1
#     start_idx = 0
#     end_idx = 1
#     repeat_words = {}
#     now_repeat = s[start_idx]
#     while True:
#         if start_idx == len(s) :
#             break
#         if s[start_idx] != s[end_idx] :
#             now_repeat += s[end_idx]
#             end_idx += 1
#             max_len += 1
#         else:
#             if s[start_idx:end_idx+1] == s[start_idx+max_len:end_idx+1+max_len]:
#                 if not repeat_words.get(now_repeat):
#                     repeat_words[now_repeat] = {
#                         'len' : max_len,
#                         'cnt' : 1
#                     } = max_len
#                 start_idx += max_len
#                 end_idx += max_len
#             else:


