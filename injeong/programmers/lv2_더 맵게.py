'''
섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
모든 음식의 스코빌 지수가 K 이상이 될 때까지 반복
Question) 반복한 횟수 return
* 모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우에는 -1을 return
'''
# https://www.daleseo.com/python-heapq/
import heapq
def solution(scoville, K):
    heap = []
    for num in scoville:
        heapq.heappush(heap, num)

    answer = 0
    while len(heap)>=2:
        if heap[0] < K: # 삭제하지 않고 원소 가져오기
            f = heapq.heappop(heap) # 원소 가져오면서 삭제
            s = heapq.heappop(heap)
            new = f + s*2
            heapq.heappush(heap, new) # push 후 자동 정렬
            answer += 1
        else:
            break
    # print(heap)

    if len(heap)==1 and heap[0]<K:
        return -1
    else:
        return answer

print(solution([1, 2, 3, 9, 10, 12],7),2)
print(solution([0, 0],7),-1)