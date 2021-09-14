import sys
sys.stdin = open("input.txt", "r")
'''
정수 N이 주어졌을 때, 
N 이 1 이상 9 이하의 두 수 a, b의 곱으로 표현될 수 있는지 판단하라.
'''
if __name__ == '__main__' :
    for tc in range(1, int(input())+1):
        answer = 'No'
        N = int(input())
        for i in range(1, 10):
            q, r = divmod(N, i)
            if r==0 and 1<=q<=9: 
                answer = 'Yes' 
                break
        print('#{} {}'.format(tc, answer))