import sys
sys.stdin = open("input_1129.txt", "r")
'''
[question]

[output]
입력에서 0이 주어진 횟수만큼 답을 출력한다. 
만약 배열이 비어 있는 경우인데 가장 작은 값을 출력하라고 한 경우에는 0을 출력하면 된다.
'''

if __name__ == '__main__':
    import heapq
    # 최소 힙 트리
    heap = [] # 변수명은 무조건 heap. 일반 리스트는 heapify로 힙 자료형으로 변경 해줘야 함
    for tc in range(int(input())):
        n = int(input())
        if n != 0:
            heapq.heappush(heap, n)
        elif heap==[]:
            print(0)
        else:
            print(heapq.heappop(heap))
    