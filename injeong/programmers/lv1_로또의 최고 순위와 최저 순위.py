'''
1	6개 번호가 모두 일치
2	5개 번호가 일치
3	4개 번호가 일치
4	3개 번호가 일치
5	2개 번호가 일치
6(낙첨)	그 외
Question) 모르는 번호가 있을 떄, 당첨될 수 있는 최고/최저 순위?
'''

def solution(lottos, win_nums):
    answer = [7, 7]
    for num in lottos:
        if num in win_nums:
            answer[0] -= 1
            answer[1] -= 1
        elif num==0:
            answer[0] -= 1
    if answer[0]==7:
        answer[0]=6
    if answer[1]==7:
        answer[1]=6
    return answer

print(solution([44, 1, 0, 0, 31, 25],[31, 10, 45, 1, 6, 19]),[3, 5])
print(solution([0, 0, 0, 0, 0, 0],[38, 19, 20, 40, 15, 25]),[1, 6])
print(solution([45, 4, 35, 20, 3, 9],[20, 9, 3, 45, 4, 35]),[1, 1])
print(solution([1,2,3,4,5,6],[7,8,9,10,11,12]),[6, 6])