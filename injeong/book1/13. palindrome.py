# 팰린드롬 연결리스트

import collections

class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class linkedlistSolution:
    # 1. 리스트로 변환
    def isPalindrome(self, head: ListNode) -> bool :
        q: list = []
        if not head:
            return True
        
        node = head
        # 리스트 변환
        while node is not None:
            q.append(node.val)
            node = node.next
        
        # 팰린드롬 판별
        while len(q) > 1:
            if q.pop(0) != q.pop():
                return False
        return True
        
    
    # 2. 데크 자료형을 이용한 최적화
    def isPalindrome2(self, head: ListNode) -> bool :
        # 자료형을 데크로 선언
        q: Deque = collections.deque()

        if not head:
            return True
        
        node = head
        # 리스트 변환
        while node is not None:
            q.append(node.val)
            node = node.next

        while len(q) > 1 :
            if q.popleft() != q.pop() :
                return False
        return True


if __name__ == '__main__' :
    l = linkedlistSolution()
    l1 = ListNode(1)
    l1.next = ListNode(2)
    l2 = ListNode(1)
    l2.next = ListNode(2)
    l2.next.next = ListNode(2)
    l2.next.next.next = ListNode(1)
    print(l.isPalindrome(l1))
    print(l.isPalindrome(l2))
    print(l.isPalindrome2(l1))
    print(l.isPalindrome2(l2))