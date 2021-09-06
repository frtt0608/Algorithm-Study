# 조합
# 전체 수 n을 입력받아 k개의 조합을 리턴해라.

if __name__=='__main__':
    def combine(n: int, k: int) -> list[list[int]]:
        result = []

        def dfs(elements, start:int, k: int):
            # 종료 조건 : 더 이상 뽑지 않아도 될 때
            if k == 0 :
                result.append(elements[:])
                return
            
            # 뽑은 값 제외 고정
            for i in range(start, n+1):
                elements.append(i)
                dfs(elements, i+1, k-1)
                elements.pop()
                
        dfs([], 1, k) # 뽑은 리스트, 시작 수, 뽑을 개수
        return result


    print(combine(4, 2))