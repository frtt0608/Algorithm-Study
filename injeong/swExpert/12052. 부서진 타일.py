'''
NxM 격자에서 부서진 타일을 대체하려고 한다. 2x2 타일로 모두 대체가 가능하면 YES, 아니라면 NO를 출력해라.
'''
import sys
sys.stdin = open("input.txt", "r")

if __name__ == '__main__' :
    for tc in range(1, int(input())+1):
        n, m = map(int, input().split())
        maps = []
        for ni in range(n):
            maps.append(list(input()))

        for p in maps:
            print(p)
        # DFS 활용?
        flag = True
        for i in range(n):
            for j in range(m):
                if maps[i][j] == '#' :
                    if i<n-1 and j<m-1 :
                        if [maps[i][j], maps[i][j+1], maps[i+1][j], maps[i+1][j+1]] == ['#','#','#','#'] :
                            maps[i][j] = '.'
                            maps[i][j+1] = '.'
                            maps[i+1][j] = '.'
                            maps[i+1][j+1] = '.'
                        else:
                            flag = False
                    elif i==n-1 or j==m-1 :
                        flag = False
                # print(maps)
        if flag:
            print("#{} {}".format(tc, "YES"))
        else:
            print("#{} {}".format(tc, "NO"))

                    

