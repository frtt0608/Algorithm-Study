# 세 수의 합
# 합이 0이 될 수 있는 3개의 값 출력

class listSolution:
    # 1. bruteforce
    def threesum(self, nums: list[int]) -> list[list[int]]:
        result = []
        nums.sort()
        for i in range(len(nums)-2):
            # 중복된 값 건너뛰기
            if i>0 and nums[i]==nums[i-1]: continue
            if nums[i] > 0 : continue
            for j in range(i+1, len(nums)-1):
                if j>i+1 and nums[j]==nums[j-1]: continue
                if nums[i]+nums[j] > 0 : continue
                for k in range(j+1, len(nums)):
                    if k>j+1 and nums[k]==nums[k-1]: continue
                    if nums[i]+nums[j]+nums[k]==0:
                        result.append([nums[i],nums[j],nums[k]])
        return result
                        

    # 2. 투 포인터로 합 계싼                  
    def threesum2(self, nums: list[int]) -> list[list[int]]:
        result = []
        nums.sort()
        for i in range(len(nums)-2):
            if i>0 and nums[i]==nums[i-1]: continue
            left, right = i+1, len(nums)-1
            while left<right:
                sums = nums[i] + nums[left] + nums[right]
                if sums > 0 :
                    right -= 1
                elif sums < 0 :
                    left += 1
                else:
                    # 정답 추가
                    result.append([nums[i],nums[left],nums[right]])
                    # 다음 스킵 처리
                    while left<right and nums[left]==nums[left+1]:
                        left += 1
                    while left<right and nums[right]==nums[right-1]:
                        right -= 1
                    left += 1
                    right -= 1
        return result
     

if __name__ == '__main__' :
    l = listSolution()
    print(l.threesum([-1, 0, 1, 2, -1, -4]))
    print(l.threesum2([-1, 0, 1, 2, -1, -4]))