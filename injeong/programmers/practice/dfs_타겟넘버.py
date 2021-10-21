# DFS/BFS
'''
n개의 음이 아닌 정수
이 수를 적절히 더하거나 빼서 타겟 넘버를 만들려고 합니다. 
예를 들어 [1, 1, 1, 1, 1]로 숫자 3을 만들려면 다음 다섯 방법을 쓸 수 있습니다.
-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3
Question) 모든 경우의 수는?
'''
def solution(numbers, target):
    n = len(numbers)

    # DFS : +/-를 결정하면서 다음 넘버로 가는 것
    def dfs(v, elements, result):
        if v==n:
            if sum(elements)==target:
                if elements not in result:
                    result.append(elements)
                return result
        for i in range(v, n):
            visited.append(i)
            if i==0 or (len(visited)>=2 and visited[-1]-visited[-2]==1):
                result = dfs(v+1, elements+[numbers[i]], result)
                result = dfs(v+1, elements+[numbers[i]*(-1)], result)
            visited.pop(-1)
        return result

    visited = []
    answer = len(dfs(0, [], []))
    return answer

print(solution([1, 1, 1, 1, 1], 3), 5)
print(solution([1, 2, 1, 2], 2), 3)
print(solution([1, 2, 1, 2], 6), 1)
