'''
스파이가 가진 의상들이 담긴 2차원 배열 clothes가 주어질 때 
서로 다른 옷의 조합의 수를 return하기
- 스파이는 하루에 최소 한 개의 의상은 입습니다
'''

def solution(clothes):
    dicts = {}
    for x in clothes:
        if x[1] not in dicts.keys():
            dicts[x[1]] = 0
        dicts[x[1]]+=1

    answer = -1 # 모두가 0개를 선택한 경우 빼주기
    tmp_ans = 1
    for i in dicts.values():
        tmp_ans *= (i+1) # 0개선택 ~ 최대n개선택(n+1)
    answer += tmp_ans
    return answer

print(solution([["yellowhat", "headgear"], ["bluesunglasses", "eyewear"], ["green_turban", "headgear"]]), 5)
print(solution([["crowmask", "face"], ["bluesunglasses", "face"], ["smoky_makeup", "face"]]), 3)