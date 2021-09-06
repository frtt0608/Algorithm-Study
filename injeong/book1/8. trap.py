# 빗물 트래핑
# 높이를 입력받아 비 온 후 얼마나 많은 물이 쌓일 수 있는지 계산

class listSolution:
    # 투 포인터 활용
    def trap(self, height: list[int]) -> int:
        if not height: return 0

        volume = 0
        left, right = 0, len(height)-1
        left_max, right_max = height[left], height[right] # 0, 1

        while left < right :
            left_max, right_max = max(height[left], left_max), max(height[right], right_max)
            # 더 높은 쪽을 향해 포인터 이동
            if left_max <= right_max:
                volume += left_max-height[left]
                left += 1
            else:
                volume += right_max-height[right]
                right -= 1
        return volume

    # stack 활용
    def trap2(self, height: list[int]) -> int:
        stack = []
        volume = 0

        for i in range(len(height)):
            # 변곡점을 만나는 경우
            while stack and height[i] > height[stack[-1]]:
                # 스택에서 꺼낸다.
                top = stack.pop()
                
                if not len(stack):
                    break

                # 이전과의 차이만큼 물 높이 처리
                distance = i-stack[-1]-1
                waters = min(height[i], height[stack[-1]]-height[top])

                volume += distance * waters
            stack.append(i)
        return volume


if __name__ == '__main__' :
    l = listSolution()
    print(l.trap([0,1,0,2,1,0,1,3,2,1,2,1])) # 6
    print(l.trap2([0,1,0,2,1,0,1,3,2,1,2,1])) # 6