'''
0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.
예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고,
이중 가장 큰 수는 6210입니다.

0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때, 
순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return
'''

if __name__ == '__main__':
    def solution(numbers):
        answer = '0'
        # 순열
        array = [] # 임시 결과
        def dfs(elements):
            # 만들어야 할 배열
            nonlocal answer
            if array and int(answer[0]) > array[0]:
                return
            if len(elements) == 0:
                if int(answer) < int(''.join(map(str, array))):
                    answer = ''.join(map(str, array))
            else:
                for i in elements:
                    next_el = elements[:]
                    next_el.remove(i)
                    array.append(i)
                    dfs(next_el)
                    array.pop()
        dfs(numbers) # [6, 10, 2]

        return answer
    
    print(solution([6, 10, 2]), "6210")
    print(solution([3, 30, 34, 5, 9]),"9534330")