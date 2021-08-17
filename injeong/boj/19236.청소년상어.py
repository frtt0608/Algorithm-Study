'''
'''
import sys
sys.stdin = open("input2.txt", "r")

# DFS
# 하나 선택 후 다음 레벨로 가서 최종에서 물고기의 합 계산
# 이전 레벨로 돌아와서 다음 꺼 선택했을 때 계산
# ...
if __name__ == '__main__':
    def printf(data):
        for d in data:
            print(d)
        print("==================")
    for tc in range(1, int(input())+1):
        maps = []
        dirs = [] # 위부터 반시계방향 : 1~8
        dy = [-1, -1, 0, 1, 1, 1, 0, -1] # 행
        dx = [0, -1, -1, -1, 0, 1, 1, 1] # 열
        for i in range(4):
            x = list(map(int, input().split()))
            a, b = [], []
            for j in range(0, 8, 2):
                a.append(x[j])
                b.append(x[j+1])
            maps.append(a)
            dirs.append(b)
            
        printf(maps)
        printf(dirs)
        answer = 0 #상어가 먹은 물고기의 최대 합

        
        print(tc, answer)