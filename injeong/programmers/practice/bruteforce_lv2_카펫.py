# 완전탐색 문제
'''
Conditions) 테두리 1줄만 모두 brown & brown/yellow 격자 수
            가로 길이는 세로 길이와 같거나, 세로 길이보다 길다.
Question) nxm 격자일 때, [n,m](가로, 세로 크기)을 구해라
'''
def solution(brown, yellow):
    answer = []
    # brown : n x m => n*m-yellow = brown
    # yellow : (n-2) x (m-2) => (n-2)*(m-2) = yellow
    # brown = n*m-(n-2)*(m-2)=n*m-(n*m-2*n-2*m+4)=2*(n+m)-4
    # k = n+m (n>=m)
    # l = n*m = yellow+brown
    k = (brown+4)//2
    l = brown+yellow
    for i in range(1, k//2+1):
        m = i
        n = k-i
        if n+m == k and n*m == l:
            answer.append(n)
            answer.append(m)
    return answer


print(solution(10, 2),[4, 3])
print(solution(8,1),[3, 3])
print(solution(24,24), [8, 6])
