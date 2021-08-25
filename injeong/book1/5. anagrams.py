# 그륩 애너그램
# 애너그램 : 문자를 재배열하여 다른 뜻을 가진 단어로 바꾸는 것
# -> 정렬하여 비교하기

import collections


class strSolution:
    def groupAnagrams(self, strs: list[str]) -> list[list[str]]:
        anagrams = collections.defaultdict(list)

        for word in strs:
            # 1. 정렬하여 딕셔너리에 추가
            anagrams[''.join(sorted(word))].append(word)
        return anagrams.values()

    '''
    # 그 외 정렬 방법
    1. 문자열 길이로 정렬
        sorted(str, key=len)
    2. 특정 위치의 문자로 정렬
        1) 함수 이용
        def fn(s):
            return s[0], s[-1]
        sorted(str, key=fn)
        2) 람다 이용
        str.sort(key=lamda x : (x[0], x[-1]))
        sorted(str, key=lamda x : (x[0], x[-1]))
    '''

if __name__ == '__main__' :
    s = strSolution()
    print(s.groupAnagrams(["eat", "tea", "tan", "ate", "nat", "bat"]))