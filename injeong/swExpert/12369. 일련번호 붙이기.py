import sys
sys.stdin = open("input.txt", "r")

'''
A B AA AB BA BB AAA AAB ABA ABB BAA BAB(12) BBA BBB
-> A와 B로 만들 수 있는 중복순열
l개로 만든다면, 1자리=l개, 2자리=l*l개, 3자리=l*l*l개
'''

import sys
sys.setrecursionlimit(1000000) # 재귀 깊이 늘려주기(파이썬은 기본으로 1000으로 제한)

if __name__ == '__main__' :
    for tc in range(1, int(input())+1):
        word, N = input().split(' ')
        word, l, N = sorted(list(word)), len(word), int(N)
        # print(word, l, N)
        # l개로 1개부터 중복순열 만들어서 N번째 글자 찾기

        result = []
        def perm(n, r, N): # n개 중 r개(중복가능) cnt번째
            global cnt, answer
            if r==0 :
                cnt += 1
                if cnt == N :
                    answer = ''.join(result)
                    return
                return
            for i in range(n):
                result.append(word[i])
                perm(n, r-1, N)
                result.pop()
                if answer != '' : break
            return
        
        # 몇자리 찾으면 되는지 자리수 k 찾기
        i, s = 1, 0
        while s < N :
            s += l**i
            i += 1
        cnt, k = s-l**(i-1), i-1

        answer = ''
        perm(l, k, N)
        
        print('#{} {}'.format(tc, answer))


        