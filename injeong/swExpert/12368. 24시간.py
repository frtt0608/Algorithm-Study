import sys
sys.stdin = open("input.txt", "r")

'''
지금은 자정에서부터 정확히 A시간이 지났다. 
앞으로 정확히 B시간이 더 지난다면, 
24시간제 시계에서 그 때는 몇 시일까?
'''
if __name__ == '__main__' :
    for tc in range(1, int(input())+1):
        A, B = map(int, input().split())
        answer = (A+B)%24
        print('#{} {}'.format(tc, answer))