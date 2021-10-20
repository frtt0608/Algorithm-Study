'''
n개의 음이 아닌 정수가 있습니다. 이 수를 적절히 더하거나 빼서 타겟 넘버를 만들려고 합니다. 
예를 들어 [1, 1, 1, 1, 1]로 숫자 3을 만들려면 다음 다섯 방법을 쓸 수 있습니다.
-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3
'''
if __name__ == '__main__':
    def solution(numbers, target):
        result = 0
        def dfs(cnum, index, elements):
            nonlocal result
            if len(elements) == len(numbers):
                if cnum == target:
                    result += 1
                    return

            for i in range(index, len(numbers)):
                dfs(cnum + numbers[i], i+1, elements+[numbers[i]])
                dfs(cnum - numbers[i], i+1, elements+[-1*numbers[i]])
            return result

        return dfs(0, 0, [])

    print(solution([1, 1, 1, 1, 1], 3), 5)
    print(solution([1, 2, 1, 2], 2), 3)
    print(solution([1, 2, 1, 2], 6), 1)