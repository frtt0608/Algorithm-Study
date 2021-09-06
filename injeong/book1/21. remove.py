# 중복 문자 제거
# 제거 후 사전식 순서로 나열
# - 사전식 순서 : 중복 문자를 앞에서부터 지우기

import collections

class stackSolution:
    # 1. stack을 이용한 문자 제거
    def removeDup(self, s: str) -> str:
        # 차례대로 쌓다가 현재 문자가 쌓인 문자보다 앞에 있고,
        # 쌓인 문자가 뒤에 더 있다면 꺼내서 삭제하기.
        # stack에서의 연산 따로. seen(set집합)은 검색 연산 수행용
        counter, seen, stack = collections.Counter(s), set(), [] # 문자개수, 이미 처리된 문자, 스택

        for char in s:
            counter[char] -= 1
            # 이미 처리되었으면 스킵
            if char in seen:
                continue
            '''
            # 현재 문자보다 뒤 순서고, 만약 뒤에 붙일 문자가 남아있다면 stack에서 제거
            # 'a'<'b' : True (문자끼리도 대소 비교 가능. 즉, 순서 비교 가능)
            '''
            while stack and char < stack[-1] and counter[stack[-1]] > 0:
                seen.remove(stack.pop())
            stack.append(char)
            seen.add(char)
        return ''.join(stack)


if __name__ == '__main__' :
    s = stackSolution()
    print(s.removeDup("bcabc")) # abc
    print(s.removeDup("cbacdcbc")) # acdb