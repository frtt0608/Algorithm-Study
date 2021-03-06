'''
전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인.
예) 
구조대 : 119
박준영 : 97 674 223
지영석 : 11 9552 4421
-> 구조대 번호는 영석이 번호의 접두어
-> 번호끼리 접두어면 false, 아니면 true 반환해라.
'''
# 정확성(100%)과 효율성(50%)을 따지는 문제였음.
# def solution(phone_book):
#     phone_book.sort(key=len) # 정렬한 다음,
#     answer = True
#     i = 0
#     j = i+1
#     while i<len(phone_book)-1:
#         l = len(phone_book[i]) # 기준 번호의 길이
#         if phone_book[i] == phone_book[j][0:l]: # 비교대상 번호의 접두어인지
#             answer = False
#             break
#         else:
#             j+=1
#             if j == len(phone_book):
#                 i += 1
#                 j = i+1
#     return answer

def solution(phone_book):
    phone_book.sort(key=len) # 정렬한 다음,
    dicts = {}
    len_lists = []
    answer = True
    for i in range(len(phone_book)):
        # add to dictionary & list
        now_phone = phone_book[i]
        dicts[now_phone] = []
        # 모두 비교하는 것이 아니라,
        # 앞서있는 len 만큼만 비교해서 넣으면 된다.
        for l in len_lists:
            if now_phone[0:l] != now_phone and now_phone[0:l] in dicts.keys():
                dicts[now_phone].append(now_phone[0:l])
                answer = False
        n = len(now_phone)
        if n not in len_lists:
            len_lists.append(n)

    return answer

print(solution(["119", "97674223", "1195524421"]), False)
print(solution(["123","456","789"]), True)
print(solution(["12","123","1235","567","88"]), False)