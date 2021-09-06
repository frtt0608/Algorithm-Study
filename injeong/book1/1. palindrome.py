# 문자열이 팰린드롬인지 확인하기.


import collections
import re

class strSolution:
    # 1. 리스트로 변환
    def isPalindrome(self, s:str) -> bool :
        strs = []
        for char in s :
            if char.isalnum(): # 영어와 숫자 판별
                strs.append(char.lower())
        # 팰린드롬 여부 판별
        while len(strs) > 1 :
            if strs.pop(0) != strs.pop() :
                return False # 함수 종료 및 결과 반환
        return True
    
    # 2. 데크 자료형을 이용한 최적화
    def isPalindrome2(self, s:str) -> bool :
        # 자료형을 데크로 선언
        strs: Deque = collections.deque()
        for char in s :
            if char.isalnum():
                strs.append(char.lower())
        while len(strs) > 1 :
            # pop(0):O(n)보다 popleft():O(1)가 빠르다.
            if strs.popleft() != strs.pop() :
                return False
        return True

    # 3. 슬라이싱 사용 : 2번과 비교해서 속도가 2배 빠름
    def isPalindrome3(self, s:str) -> bool :
        s = s.lower()
        # 정규식으로 불필요한 문자열 제거
        s = re.sub('[^a-z0-9]','',s) #^__ : __제외 나머지 대체
        return s == s[::-1]

if __name__ == '__main__' :
    s = strSolution()
    print(s.isPalindrome("A man, a plan, a canal: Panama"))
    print(s.isPalindrome2("A man, a plan, a canal: Panama"))
    print(s.isPalindrome3("A man, a plan, a canal: Panama"))