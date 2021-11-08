'''
가로 세로가 주어질 때, 대각선으로 자른 경우
사용 가능한 1x1 정사각형의 개수
'''


# # 소수인지 확인
# def isprime(n):
# if n!=1:
#     for i in range(2, n):
#         if n%i==0 :
#             return False
# return True
# # 최대공약수 찾기
# def gcf(x, y):
# result = 1
# if w==1 or h==1:
#     return result
# i = 2
# while True:
#     if x%i==0 and y%i==0:
#         result *= i
#         x //= i
#         y //= i
#         i = 2
#         if isprime(x) and isprime(y):
#             break
#     else:
#         i += 1
# return result

import math
def solution(w,h):
    g = math.gcd(w,h)
    return w*h - (w+h-g)

print(solution(8,12),80)
print(solution(1,1),0)
print(solution(2,4),4)
print(solution(5,5),20)