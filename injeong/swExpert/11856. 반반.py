'''
S에 정확히 두 개의 서로 다른 문자가 등장하고, 각 문자가 정확히 두 번 등장하는 지 판별해라.
Yes or No
'''
import sys
sys.stdin = open("input.txt", "r")

if __name__ == '__main__' :
    for tc in range(1, int(input())+1):
        s = input()
        a_s = sorted(s)
        tmp_s = sorted(set(list(s)))

        if len(tmp_s) == 2 and a_s[1] != a_s[2] :
            answer = 'Yes'
        else:
            answer = "No"

        print('#{} {}'.format(tc, answer))