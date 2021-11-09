'''
섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
모든 음식의 스코빌 지수가 K 이상이 될 때까지 반복
Question) 반복한 횟수 return
* 모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우에는 -1을 return
'''
def solution(scoville, K):
    scoville = sorted(scoville, reverse=True)
    
    answer = 0
    while len(scoville)>=2:
        if scoville[-1] < K:
            f = scoville.pop(-1)
            s = scoville.pop(-1)
            new = f + s*2
            scoville.append(new)
            answer += 1
            scoville = sorted(scoville, reverse=True)
        else:
            break

    if len(scoville)==1 and scoville[-1]<K:
        return -1
    else:
        return answer

print(solution([1, 2, 3, 9, 10, 12],7),2)
print(solution([0, 0],7),-1)