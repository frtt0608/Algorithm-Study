# 순열
# 서로 다른 정수를 입력받아 가능한 모든 순열을 리턴하라.
'''
시작 노드부터 리프노드까지 자식의 수가 점점 줄어든다.
[1,2,3] -> [] -> [1],[2],[3] (3개)->[[1,2],[1,3]] (2개),...]
=> 최종 결과는 리프노드의 값이다.
'''

if __name__=='__main__':
    def permutations(nums: list[int]) -> list[list[int]]:
        results = [] # 결과 리스트
        prev_elements = [] # 일시적인 결과 생성하는 리스트

        # dfs 활용한 순열 생성
        def dfs(elements): # [1,2,3]
            # 리프 노드일 때 값 추가 : 모든 숫자를 다썼으면,
            if len(elements) == 0:
                results.append(prev_elements[:])
            
            # 재귀 호출
            for e in elements: # 1, 2, 3
                next_elements = elements[:]
                next_elements.remove(e) # 하나를 빼서

                prev_elements.append(e) # 하나를 추가
                dfs(next_elements) # 남은 요소들로 다시 dfs 호출
                prev_elements.pop() # dfs 끝나면 다시 빼기
        
        dfs(nums)
        return results

    print(permutations([1,2,3]))
