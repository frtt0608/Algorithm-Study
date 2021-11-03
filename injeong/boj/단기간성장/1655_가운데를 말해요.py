import sys
sys.stdin = open("input.txt", "r")

# Gold 2
'''
지금까지 말한 수 중에서 중간값 말하기
만약, 짝수라면 둘 중 작은 수 말하기
'''

if __name__=='__main__':
    N = int(input())
    report = [-10000, 10000]
    mid_idx = 0
    l = 2
    for k in range(N):
        n = int(input())
        # print(report)
        l += 1
        if n >= report[mid_idx]:
            for i in range(mid_idx+1, len(report)):
                if n <= report[i]:
                    report.insert(i, n)
                    if l%2: # 홀수면 / 짝수면 그대로
                        mid_idx += 1
                    break
        else:
            for i in range(mid_idx-1, -1, -1):
                if report[i] < n :
                    report.insert(i+1, n)
                    if not l%2: # 짝수면,
                        mid_idx -= 1
                    else: # 홀수면,
                        mid_idx += 1
                    break
        print(report[mid_idx])
