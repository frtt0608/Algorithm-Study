import sys
sys.stdin = open("input.txt", "r")

'''
A B AA AB BA BB AAA AAB ABA ABB BAA BAB(12) BBA BBB
-> A와 B로 만들 수 있는 중복순열
l개로 만든다면, 1자리=l개, 2자리=l*l개, 3자리=l*l*l개
'''
if __name__ == '__main__' :
    for tc in range(1, int(input())+1):
        word, N = input().split(' ')
        word, l, N = sorted(list(word)), len(word), int(N)

        k = 0
        while N > 0:
            k += 1
            if N - l**k > 0:
                N -= l**k
                continue
            else:
                break
        # k자리수로 시작/N만큼 가면 됨

        answer = ''
        for i in range(k): # 자리수만큼 돌면서
            jaja = (l**(k-i-1)) # 첫번째 자리 고정이면 경우의 수는 l**(l-1)
            for idx, alp in enumerate(word): # 어떤 수가 고정인 지 찾는 것.
                if N - jaja > 0: # A부터 고정인 경우를 빼가면서 정리
                    N -= jaja
                    continue
                else: 
                    answer += alp
                    break
        
        print('#{} {}'.format(tc, answer))


        