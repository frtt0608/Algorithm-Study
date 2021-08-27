'''
컴퓨터의 개수 n, 연결에 대한 정보가 담긴 2차원 배열 computers가 매개변수로 주어질 때, 
네트워크의 개수를 return 하도록 solution 함수를 작성하시오.
[i][i]는 무조건 1
'''

if __name__ == '__main__':
    def solution(n, computers):
        graph = {}
        for i in range(n):
            graph[i] = []
            for j in range(n):
                if i!=j and computers[i][j]==1:
                    graph[i].append(j)
        # print(graph)


        def dfs(v, visited=[]):
            visited.append(v)
            for w in graph[v]:
                if w not in visited:
                    visited = dfs(w, visited)
            return visited

        answer, i = 0, 0
        chk = []
        while set(chk) != set([x for x in range(n)]):
            if i not in chk:
                chk = dfs(i)
                answer += 1
            else:
                i += 1
            # print(chk)

        return answer

    print(solution(3, [[1, 1, 0], [1, 1, 0], [0, 0, 1]]), 2)
    print(solution(3, [[1, 1, 0], [1, 1, 1], [0, 1, 1]]), 1)
