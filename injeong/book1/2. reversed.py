# 문자열 뒤집기

class strSolution:
    # 1. 투 포인터를 이용한 스왑
    def reverseString(self, s:list[str]) -> None:
        left, right = 0, len(s)-1
        while left < right:
            s[left], s[right] = s[right], s[left]
            left += 1
            right -= 1
        return s

    # 2. 내장함수 사용
    def reverseString2(self, s:list[str]) -> None:
        s.reverse()
        s[:] = s[::-1]
        return s
        
    
if __name__ == '__main__' :
    s = strSolution()
    print(s.reverseString(["h", "e", "l", "l", "o"]))
    print(s.reverseString2(["H", "e", "l", "l", "o"]))