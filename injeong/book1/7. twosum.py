# 두 수의 합
# 덧셈하여 타겟을 만들 수 있는 배열의 두 숫자 인덱스 리턴

class listSolution:
    # 1. bruteforce
    def twosum(self, nums: list[int], target:int) -> list[int]:
        for i in range(len(nums)):
            for j in range(i+1, len(nums)):
                if nums[i]+nums[j] == target:
                    return [i, j]

    # 2. in을 이용한 탐색                    
    def twosum2(self, nums: list[int], target:int) -> list[int]:
        # target - n 이 리스트에 있는지 판별
        for i, n in enumerate(nums):
            complement = target-n
            if complement in nums[i+1:]:
                return [i, nums.index(complement)]
    
    # 3. 첫 번째 수를 뺀 결과 키 조회 : 속도 개선                
    def twosum3(self, nums: list[int], target:int) -> list[int]:
        # key, value : n, idx
        nums_map=  {}
        for i, n in enumerate(nums):
            nums_map[n] = i
        for i, n in enumerate(nums):
            if target-n in nums_map and i != nums_map[target-n]:
                return [i, nums_map[target-n]]
            
    # 4. 투 포인터 활용(정렬했다고 가정) : 1번과 90배 속도 차이
    def twosum4(self, nums: list[int], target:int) -> list[int]:
        # nums.sort() : index가 꼬일 수 있으므로 조심할 것.
        left, right = 0, len(nums)-1
        while not left==right:
            # 합이 타겟보다 작으면 left+1
            if nums[left]+nums[right]<target:
                left += 1
            elif nums[left]+nums[right]>target:
                right -= 1
            else:
                return [left, right]


        
            
        

if __name__ == '__main__' :
    l = listSolution()
    print(l.twosum([2, 7,11,15], 9)) # [0,1]
    print(l.twosum2([2, 7,11,15], 9)) # [0,1]
    print(l.twosum3([2, 7,11,15], 9)) # [0,1]
    print(l.twosum4([2, 7,11,15], 9)) # [0,1]