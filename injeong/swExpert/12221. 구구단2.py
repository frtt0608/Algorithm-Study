'''
1~9끼리만 곱셈 할 수 있다.
10 이상은 불가능하다.
주어진 두 정수가 곱이 가능하면 곱한 값을, 아니라면 -1을 출력해라.
'''
import sys
sys.stdin = open("input.txt", "r")

if __name__ == '__main__' :
    for tc in range(1, int(input())+1):
        a, b = map(int, input().split())
        answer = a*b
        if a>=10 or b>=10 :
            answer = -1
        print('#{} {}'.format(tc, answer))