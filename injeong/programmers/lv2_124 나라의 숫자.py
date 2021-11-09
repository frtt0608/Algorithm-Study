'''
10진법이 아닌 다음과 같은 자신들만의 규칙으로 수를 표현한다
- 124 나라에는 자연수만 존재합니다.
- 124 나라에는 모든 수를 표현할 때 1, 2, 4만 사용합니다.
Question) n을 124 나라에서 사용하는 숫자로 바꾼 값을 return
'''

def solution(n):
    elements = ['1', '2', '4'] # [1,2,4]를 중복해서 k번 뽑는 것
    # 재귀 이용
    # ex) 15 => 14 = (4,2) => 나머지 2는 '4'
    #                 4-1=3 => (1, 0) => 나머지 0은 '1'
    #                           1-1=0 => (0, 0) => 나머지 0은 '1'
    # 이런 규칙으로 재귀 가능.
    def recursion(n):
        nonlocal answer
        if n<0:
            return
        q, r = divmod(n, 3)
        answer = elements[r] + answer
        return recursion(q-1)
    
    answer = ''
    recursion(n-1)

    return answer

print(solution(1),1)
print(solution(2),2)
print(solution(3),4)
print(solution(4),11)
print(solution(15),114)
# print(solution(500000000),)