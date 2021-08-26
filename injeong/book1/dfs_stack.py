# 그래프 순회(탐색) : DFS


if __name__=='__main__':
    graph = {
        1: [2, 3, 4],
        2: [5],
        3: [5],
        4: [],
        5: [6, 7],
        6: [],
        7: [3],
    }
    # 1. 재귀 구조로 구현
    def recursive_dfs(v, discovered=[]):
        discovered.append(v) # 지나간 노드 체크
        for w in graph[v]: # 인접 노드들
            if w not in discovered: # 안 지나갔으면,
                discovered = recursive_dfs(w, discovered) # 다음 노드로 지정
        return discovered

    # 2. 스택을 이용한 반복 구조로 구현
    def iterative_dfs(start_v):
        discovered = []
        stack = [start_v]
        while stack:
            v = stack.pop()
            if v not in discovered:
                discovered.append(v)
                for w in graph[v]:
                    stack.append(w)
        return discovered

    print(recursive_dfs(1)) # 시작점
    print(iterative_dfs(1))
