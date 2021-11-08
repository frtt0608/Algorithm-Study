'''
10진법이 아닌 다음과 같은 자신들만의 규칙으로 수를 표현한다
- 124 나라에는 자연수만 존재합니다.
- 124 나라에는 모든 수를 표현할 때 1, 2, 4만 사용합니다.
Question) n을 124 나라에서 사용하는 숫자로 바꾼 값을 return
'''

def solution(n):
    elements = ['1', '2', '4'] # [1,2,4]를 중복해서 k번 뽑는 것
    # 등비수열의 합공식 이용
    k = 1
    while 3**(k+1) < 2*n+3:
        k += 1
    if k!=1:
        r = n-((3**k-3)//2) # r=3이고 k-1개의 합을 빼주면 k자리수+몇번째 뽑아야 하는지 나옴
    else:
        r = n
    # print(k, r)

    def comb(k, curr):
        nonlocal r, answer
        # 3개 중 k자리수 뽑기/현재 curr개 뽑음
        if curr==k:
            r -= 1
            if r==0:
                answer = ''.join(result)
            return 
        for i in range(3):
            result.append(elements[i])
            comb(k, curr+1)
            result.pop(-1)
        return

    answer = ''
    result = []
    comb(k, 0)

    return answer

# print(solution(1),1)
# print(solution(2),2)
# print(solution(3),4)
# print(solution(4),11)
# print(solution(13),111)
print(solution(500000000),)