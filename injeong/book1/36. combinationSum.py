# 조합의 합 : 중복 조합
# 전체 수 n을 입력받아 모든 조합 중 합이 target이 되는 원소를 나열해라.
# 각 원소는 중복으로 나열 가능하다.
# ex) [2,3,6] 4 -> [2,2]

if __name__=='__main__':
    def combineSum(candidates: list[int], target: int) -> list[list[int]]:
        result = []

        def dfs(elements, index:int, csum:int):
            # 종료 조건
            if csum < 0 : return # 가지치기
            if csum == 0 :
                result.append(elements[:])
                return
            
            # 자신부터 하위 원소 까지의 나열 재귀 호출
            for i in range(index, len(candidates)):
                dfs(elements+[candidates[i]], i, csum-candidates[i])

        dfs([], 0, target) # 뽑은 리스트, 시작레벨, 종료조건
        return result

    print(combineSum([2,3,6,7], 7))
