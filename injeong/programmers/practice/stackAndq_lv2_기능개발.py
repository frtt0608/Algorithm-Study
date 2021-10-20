'''
- 개발속도는 모두 다르다. 뒤에 있는 기능이 먼저 개발될 수 있다.
- 그러나 배포는 앞의 기능이 배포될 때 함께 배포된다.
- 배포는 하루에 한 번만 할 수 있으며, 하루의 끝에 이루어진다고 가정합니다. 
  예를 들어 진도율이 95%인 작업의 개발 속도가 하루에 4%라면 배포는 2일 뒤에 이루어집니다.
각 배포마다 몇 개의 기능이 배포되는지를 return해라
'''

# 작업 기준으로 험 : 실패
# def solution(progresses, speeds):
#     # progresses : 배포되어야 하는 순서
#     # speeds : 작업의 개발 속도
#     answer = []
#     days = []
#     for i in range(len(progresses)):
#         now_p = progresses[i]
#         now_s = speeds[i]
#         new_p = now_p
#         day = 0
#         while True:
#             if new_p >= 100 :
#                 break
#             new_p += now_s
#             day += 1
#         days.append(day)
#         if not answer or days[-2] < days[-1] :
#             answer.append(1)
#         else:
#             answer[-1] += 1
#     return answer

# day 기준으로 : 통과
def solution(progresses, speeds):
    # progresses : 배포되어야 하는 순서
    # speeds : 작업의 개발 속도
    answer = []
    deploys = [False]*len(progresses)
    while deploys!=[True]*len(progresses):
        for i in range(len(progresses)):
            if not deploys[i] :
                progresses[i] += speeds[i]
        tmp_ans = 0
        for j in range(len(progresses)):
            if deploys[j] : continue
            if progresses[j] >= 100 :
                deploys[j] = True
                tmp_ans += 1
            else:
                break
        if tmp_ans :
            answer.append(tmp_ans)
    return answer


print(solution([93, 30, 55], [1, 30, 5]), [2, 1])
print(solution([95, 90, 99, 99, 80, 99], [1, 1, 1, 1, 1, 1]),[1, 3, 2])