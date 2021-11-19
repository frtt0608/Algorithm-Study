'''
균형잡힌 괄호 문자열 : 짝궁 괄호가 모두 있는 경우
올바른 괄호 문자열 : 순서도 모두 맞을 경우
균형 -> 올바른 으로 변환하기
1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다. 
2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 
   단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다. 
3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다. 
  3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다. 
4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다. 
  4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다. 
  4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다. 
  4-3. ')'를 다시 붙입니다. 
  4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다. 
  4-5. 생성된 문자열을 반환합니다.
'''
def solution(p):
    # u,v 분리 : 균형 잡힌 문자열 체크
        # u가 올바른 이라면, v에 대해서 수행
        # u가 올바른이 아니라면, 4번 수행
    def separation(p):
        i = 0
        result = [0, 0]
        while True:
            if sum(result)!=0 and result[0]==result[-1]:
                break
            word = p[i]
            if word == '(':
                result[0] += 1
            else:
                result[1] += 1
            i += 1
        if i == len(p):
            return [p, '']
        return [p[:i], p[i:]]
    
    def correct(p):
        result = []
        for word in p:
            if word == '(' :
                result.append(word)
            else:
                if not result:
                    return False
                else:
                    result.pop(-1)
        return True

    def parentheses(p):
        nonlocal answer
        if p=='':
            return ''
        else:
            u, v = separation(p)
            if correct(u):
                answer += u
                parentheses(v)
                return answer
            else:
                answer += '('
                parentheses(v)
                answer += ')'
                new_u = ''
                for k, v in enumerate(u):
                    if k==0 or k==len(u)-1:
                        continue
                    else:
                        if v=='(': new_u += ')'                     
                        if v==')': new_u += '('                     
                answer += new_u
                return answer

    answer = ''
    parentheses(p)
    
    return answer

print(solution("(()())()"),"(()())()")
# print(solution(")("),"()")
# print(solution("()"),"()")
# print(solution("()))((()"),"()(())()")