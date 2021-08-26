# 일일 온도
'''
매일의 화씨 온도 리스트 T를 입력받아서, 
더 따뜻한 날씨를 위해서는 며칠(idx)을 더 기다려야 하는지를 출력.
'''

class stackSolution:
    # 1. MySolution : stack 값 비교
    def dailyTemperatures(self, T: list[int]) -> list[int]:
        stack, result = [], [0]*len(T)
        for i, cur in enumerate(T):
            while stack and T[stack[-1]]<cur:
                last = stack.pop()
                result[last] = i - last
            stack.append(i)
        return result
            

if __name__ == '__main__' :
    s = stackSolution()
    print(s.dailyTemperatures([73,74,75,71,69,72,76,73])) # [1,1,4,2,1,1,0,0]