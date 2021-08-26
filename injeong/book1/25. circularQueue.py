# 원형 큐 디자인

# 1. 배열을 이용한 디자인
class MyCircularQueue:
    def __init__(self, k: int):
        self.q = [None]*k
        self.maxlen = k
        self.p1 = 0 # front
        self.p2 = 0 # rear 
    # 삽입 연산
    def enQueue(self, value: int) -> bool:
        if self.q[self.p2] is None: # 해당 위치 값이 없으면,
            self.q[self.p2] = value          
            self.p2 = (self.p2+1)%self.maxlen # 꼬리를 한칸 앞으로 이동(원형 큐이므로 개수로 나누기)
            return True
        else:
            return False
    # 삭제 연산
    def deQueue(self, value: int) -> bool:
        if self.q[self.p1] is not None: # 해당 위치 값이 있으면,
            self.q[self.p1] = None
            self.p1 = (self.p1+1)%self.maxlen
            return True
        else:
            return False
    # 값 가져오기
    def Front(self) -> int:
        return -1 if self.q[self.p1] is None else self.q[self.p1]
    def Rear(self) -> int:
        return -1 if self.q[self.p2-1] is None else self.q[self.p2-1]
    def isEmpty(self) -> bool:
        return self.p1 == self.p2 and self.q[self.p1] is None
    def isFull(self) -> bool:
        return self.p1 == self.p2 and self.q[self.p1] is not None


if __name__ == '__main__' :
    q = MyCircularQueue(1)
    print(q.enQueue(3))
    print(q.isFull())
    print(q.deQueue(3))
    print(q.isEmpty())
