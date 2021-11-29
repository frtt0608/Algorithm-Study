# 이분탐색
'''
한 심사대에서는 동시에 한 명만 심사를 할 수 있습니다. 
가장 앞에 서 있는 사람은 비어 있는 심사대로 가서 심사를 받을 수 있습니다. 
하지만 더 빨리 끝나는 심사대가 있으면 기다렸다가 그곳으로 가서 심사를 받을 수도 있습니다.
sol)
0초 [1, 1] 4
1초 [1, 1] 4
7초 [0, 1] 4
8초 [1, 1] 3
def solution(n, times):
    now_status = [0]*len(times) # 기다려야 하는 시간
    l = len(now_status)
    cnt = [0]*len(times)
    t = 0
    while True:
        if n <= 0 :
            break
        if 0 in now_status:
            min_t = 10**9
            min_i = -1
            for i in range(l):
                if now_status[i]+times[i] <= min_t :
                    min_t = now_status[i]+times[i]
                    min_i = i
            now_status[min_i] = min_t
            cnt[min_i] += 1
            n -= 1
        else:
            pass_t = min(now_status)
            for j in range(l):
                now_status[j] -= pass_t
            t += pass_t


    # answer 갱신
    answer = max([times[i]*cnt[i] for i in range(l)])
    
    return answer
'''
# 21. 11. 29 이분탐색으로 다시 풀기
'''
왜 이분탐색인가?
심사대1(7) 심사대2(2)
3분남음 7분남음
3분 후 : 0분 4분 - 1선택시)7분 걸림=10분 2선택시)6분걸림=9분
=> 심사시간을 더한 것을 정렬해서 그 중 최소값으로 선택하면 되지 않을까?
'''
def solution(n, times):
    # 심사완료까지 걸리는 예상 시간
    judge_predict_time = times[:] # 처음은 심사 시간 [10,7]

    t = 0
    while n>0:
        wating_time = min(judge_predict_time) #7
        t += wating_time # 7초 후
        print('before', judge_predict_time)
        print('time', t)
        for i in range(len(times)):
            judge_predict_time[i] -= wating_time 
            judge_predict_time[i] += times[i]
        print('after', judge_predict_time) # [3, 0]->[13,7]
        n -= 1
            

    answer = 0
    return answer
print(solution(6,[10, 7]), 28)