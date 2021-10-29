'''
각 던전마다 탐험을 시작하기 위해 필요한 "최소 필요 피로도"와 
던전 탐험을 마쳤을 때 소모되는 "소모 피로도"가 있습니다.
k : 유저의 현재 피로도
하루에 1개 던전 탐험 가능
Question : 유저가 탐험할수 있는 최대 던전 수?
Sol)
규칙이 없다면 완전 탐색을 해야 함
최소 필요 피로도 <= 유저 피로도 인 던전을 찾아서 계속 타고 들어가는 느낌 = DFS
- 방문 횟수 누적 시키면서 최댓값 나오면 answer 갱신
==> 그래프 방문 탐색과 같아 보임
==> 조건 있는 순열과 같아 보임
'''

def solution(k, dungeons):
    l = len(dungeons)
    
    def perm(n, curr):
        # n개 순서대로 정렬
        if curr == n :
            print(visited)
            return
        for i in range(n):
            if i not in visited:
                visited.append(i)
                perm(n, curr+1)
                visited.pop(-1)
        return

    answer = -1
    visited = []
    perm(l, 0)
    return answer

print(solution(80, [[80,20],[50,40],[30,10]]), 3)