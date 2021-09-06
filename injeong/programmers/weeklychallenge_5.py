'''
사전에 알파벳 모음 'A', 'E', 'I', 'O', 'U'만을 사용하여 만들 수 있는, 
길이 5 이하의 모든 단어가 수록되어 있습니다. 
사전에서 첫 번째 단어는 "A"이고, 그다음은 "AA"이며, 마지막 단어는 "UUUUU"입니다.
단어 하나 word가 매개변수로 주어질 때, 
이 단어가 사전에서 몇 번째 단어인지 return 하도록 solution 함수를 완성해주세요.
'''
if __name__ == '__main__':
    def solution(word):
        alpha = ['.','A','E','I','O','U']
        answer = 0
        cnt = 0
        def rg(word, curr):
            nonlocal cnt, answer
            # print(word, curr, answer)
            if len(curr) > 5:
                cnt -= 1
                return
            if word == curr :
                answer = cnt
                return
            else:
                for i in range(1, 6):
                    cnt += 1
                    rg(word, curr+alpha[i])
            return
        rg(word, '')

        return answer

    print(solution("AAAE"), 10)
    print(solution("AAAAE"), 6)
    # print(solution("AAAE"), 10)
    print(solution("I"), 1563)
    print(solution("EIO"), 1189)