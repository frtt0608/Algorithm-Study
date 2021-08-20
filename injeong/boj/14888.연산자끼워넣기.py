import sys
sys.stdin = open("input.txt", "r")
'''
식의 계산은 연산자 우선 순위를 무시하고 앞에서부터 진행해야 한다. 또, 나눗셈은 정수 나눗셈으로 몫만 취한다. 
음수를 양수로 나눌 때는 C++14의 기준을 따른다. 
즉, 양수로 바꾼 뒤 몫을 취하고, 그 몫을 음수로 바꾼 것과 같다.

식의 결과가 최대인 것과 최소인 것을 구하는 프로그램을 작성해라.
'''
if __name__ == '__main__':
    def printf(data):
        for d in data:
            print(d)
        print("------------------")

    for tc in range(1, int(input())+1):
        N = int(input())
        numbers = list(map(int, input().split()))
        # 차례대로 덧셈(+)의 개수, 뺄셈(-)의 개수, 곱셈(×)의 개수, 나눗셈(÷)의 개수
        ops = ['+', '-', 'x', '/']
        operations = []
        x = list(map(int, input().split()))
        for i in range(4) :
            if x[i] != 0 :
                while x[i] != 0:
                    operations.append(ops[i])
                    x[i] -= 1

        def calc(arr):
            global numbers
            new_numbers = list(reversed(numbers))
            for i in range(N-1):
                result = 0
                a = new_numbers.pop()
                b = new_numbers.pop()
                op = operations[arr[i]]
                if op == '+' : result = (a+b)
                elif op == '-' : result = (a-b)
                elif op == 'x' : result = (a*b)
                elif op == '/' : 
                    if (a*b)<0:
                        result = -(abs(a)//abs(b))
                    else:
                        result = (abs(a)//abs(b))
                new_numbers.append(result)
            return new_numbers[-1]


        max_val = -(1e9)
        min_val = 1e9

        # 연산자 순열 : n개 중 n개 순서대로 나열하기
        chk = [False]*(N-1)
        values = []
        def perm(n, r):
            global max_val, min_val
            # n개 나열, s부터 시작
            if r == n : # n개 다 뽑은 후 결과 출력
                tmp_val = calc(values)
                if tmp_val >= max_val : max_val = tmp_val
                if tmp_val <= min_val : min_val = tmp_val
                return
            else:
                for i in range(n):
                    if not chk[i] : # 안 뽑았으면,
                        values.append(i)
                        chk[i] = True
                        perm(n, r+1) # 뽑은 후 다음 차례로 넘어가기
                        chk[i] = False # 이전 차례로 넘어와서 다른거 뽑기
                        values.pop()
        perm(N-1, 0)


        print(max_val)
        print(min_val)