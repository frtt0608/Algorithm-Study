# 로그파일 재정렬
'''
1. 로그의 가장 앞 부분은 식별자이다.
2. 문자로 구성된 로그가 숫자로 구성된 로그보다 앞에 온다.
3. 식별자는 순서에 영향을 끼치지 않지만, 문자가 동일하면 식별자 순으로 한다.
4. 숫자 로그는 입력 순서대로 한다.
'''

class strSolution:
    # 1. 람다와 +연산자를 이용
    def reorderLogF(self, logs:list[str]) -> list[str]:
        letters, digits = [], []
        for log in logs:
            if log.split()[1].isdigit():
                digits.append(log)
            else:
                letters.append(log)
        # 2개의 키를 람다 표현식으로 정렬
        # 정렬값.sort(key=lamda x : (정렬 기준1, 기준2, ..))
        letters.sort(key=lambda x: (x.split()[1:], x.split()[0])) # 문자로 정렬 -> 식별자로 정렬
        return letters+digits
        
    
if __name__ == '__main__' :
    s = strSolution()
    print(s.reorderLogF(["dig1 8 1 5 1", "let art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"]))