'''
권투선수 1번~n번
Conditions) 경기는 1:1방식
            - 상대방보다 실력이 좋으면 항상 이긴다. : [A, B]는 A 선수가 B 선수를 이겼다
Question) 몇몇 기록이 분실된 상황에서 정확하게 순위를 매길 수 있는 선수의 수?
'''
def solution(n, results):
    # graph로 표현
    win_graph = {} # 내가 항상 이길 수 있는 상대 목록
    lose_graph = {} # 내가 항상 지는 상대 목록
    for r in results:
        a = r[0] # 2
        b = r[1] # 5
        if win_graph.get(a):
            win_graph[a].append(b)
        else:
            win_graph[a] = [b]

        if lose_graph.get(b):
            lose_graph[b].append(a)
        else:
            lose_graph[b] = [a]
        

    for i in lose_graph:
        for v in lose_graph[i]:
            if lose_graph.get(v):
                for k in lose_graph[v]:
                    if i not in win_graph[k]:
                        win_graph[k].append(i)
                        lose_graph[i].append(k)

    # print(win_graph)
    # print(lose_graph)

    answer = 0
    for i in range(1, n+1):
        total = 0
        if win_graph.get(i):
           total += len(win_graph[i])
        if lose_graph.get(i):
            total += len(lose_graph[i])
        if total == n-1:
            answer += 1
    return answer

print(solution(5,[[4, 3], [4, 2], [3, 2], [1, 2], [2, 5]]),2)
print(solution(5, [[1, 2], [4, 5], [3, 4], [2, 3]]),5)

    # def dfs(n, elements):
    #     if not graph.get(n):
    #         # print(elements)
    #         return elements
    #     else:
    #         elements.extend(graph[n])
    #         elements = list(set(elements))
    #         for v in graph[n]:
    #             elements = dfs(v, elements)
    #         return elements
                
    # for i in range(1, n+1):
    #     if graph.get(i):
    #         graph[i] = dfs(i, [])
    #     else:
    #         graph[i] = []
    
    # # print(graph)
    # ranking = [0]*(n+1)
    # for n, win in graph.items():
    #     ranking[n] += len(win)
    #     for w in win:
    #         ranking[w] += 1

    # for x in ranking[1:]:
    #     if x==n-1 : answer += 1