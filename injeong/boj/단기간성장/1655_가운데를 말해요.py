import sys
sys.stdin = open("input.txt", "r")

# Gold 2
'''
지금까지 말한 수 중에서 중간값 말하기
만약, 짝수라면 둘 중 작은 수 말하기
'''

if __name__=='__main__':
    N = int(input())
    report = []
    for i in range(N):
        n = int(input())
        report.append(n)
        report = sorted(report)
        l = len(report)
        if l%2:
            print(report[l//2])
        else:
            print(min(report[l//2-1], report[l//2]))
