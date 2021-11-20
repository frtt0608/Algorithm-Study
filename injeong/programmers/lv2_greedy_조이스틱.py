'''
조이스틱으로 알파벳 이름을 완성하세요.
▲ - 다음 알파벳
▼ - 이전 알파벳 (A에서 아래쪽으로 이동하면 Z로)
◀ - 커서를 왼쪽으로 이동 (첫 번째 위치에서 왼쪽으로 이동하면 마지막 문자에 커서)
▶ - 커서를 오른쪽으로 이동

맨 처음에 A*len(완성해야 할 글자수)가 주어진다.
이름에 대해 조이스틱 조작 횟수의 최솟값을 return
'''

def solution(name):
    # 각 자리에선,
        # 위, 아래 동시 움직이다가 원하는 결과 찾으면 stop
    # 자리 이동에선,
        # visited check하면서 우, 좌 동시에 움직이다가 visited=False인 곳에서 stop
    answer = 0
    n = len(name)
    operated = [True]*n # 조작 할 필요성에 대한 여부
    pre_idx = 0
    now_idx = 0
    while True:
        word = name[now_idx]
        alpha_cnt, moving_cnt = 0, 0
        print(word, pre_idx, now_idx)
        if word != 'A':
            alpha_cnt = min(ord(word)-ord('A'), 26-(ord(word)-ord('A')))
            moving_cnt = min(abs(now_idx-pre_idx), n-abs(now_idx-pre_idx))
            pre_idx = now_idx
        operated[now_idx] = False # 조작완료
        answer += alpha_cnt
        answer += moving_cnt

        if operated == [False]*n :
            break
        print(operated)

        j = 1
        tmp_idx = now_idx
        while now_idx+j < n:
            if name[now_idx+j]!='A' and operated[now_idx+j]==True:
                tmp_idx = now_idx+j
                break
            elif name[now_idx+j]!='A' and operated[now_idx-j]==True:
                tmp_idx = n+now_idx-j
                break
            else:
                j += 1
        now_idx = tmp_idx


    return answer

# print(solution("JAZ"),11)
# print(solution("JAZAA"),12)
# print(solution("JEROEN"),56)
# print(solution("JAN"),23)
print(solution("ABAAAAAAAAABB"),7)
print(solution("ZAAAZZZZZZZ"),15)