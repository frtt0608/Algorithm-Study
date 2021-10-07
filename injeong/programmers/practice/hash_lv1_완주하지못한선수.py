def solution(participant, completion):
    
    p = participant
    c = completion
    answer = ''
    
    dp = {}
    dc = {}
    
    for i in p :
        if i in dp.keys() :
            dp[i] += 1
        else :
            dp[i] = 1
    
    for i in c :
        if i in dc.keys() :
            dc[i] += 1
        else :
            dc[i] = 1
    
    for k, v in dp.items() :
        if k not in dc.keys():
            answer = k
            break
        else :
            if (v - dc[k]) == 1 :

                answer = k
                break
        
    return answer

print(solution(["leo", "kiki", "eden"], ["eden", "kiki"]), "leo")
print(solution(["marina", "josipa", "nikola", "vinko", "filipa"], 
["josipa", "filipa", "marina", "nikola"]), "vinko")
print(solution(["mislav", "stanko", "mislav", "ana"], ["stanko", "ana", "mislav"]), "mislav")
