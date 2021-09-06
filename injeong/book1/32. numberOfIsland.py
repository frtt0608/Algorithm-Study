# 그래프의 탐색
# 1을 육지로 0을 물로 가정한 2D 그리드 맵이 주어졌을 때 섬의 개수 구해라.
# 연결되어 있는 1의 덩어리 개수를 구해라.

class graphSolution:
    # 반복된다면, grid를 클래스의 멤버 변수로 처리하자.
    # grid: list[list[int]]


    # dfs로 탐색
    def numIslands(self, grid: list[list[int]]) -> int:
        def dfs(i, j):
            # 더 이상 땅이 아닌 경우 종료
            if i<0 or len(grid)<=i or j<0 or len(grid[0])<=j or grid[i][j] != 1 : return

            grid[i][j] = 9 # 이미 방문한 곳은 체크

            # 동서남북 탐색
            dfs(i+1, j)
            dfs(i, j+1)
            dfs(i-1, j)
            dfs(i, j-1)

        # self.grid = grid # 멤버 변수로 썼을 때

        # 예외 처리 : 육지가 없을 때
        if not grid:
            return 0

        count = 0 
        for i in range(len(grid)): # 열
            for j in range(len(grid[0])): # 행
                # 육지라면, dfs탐색
                if grid[i][j] == 1:
                    dfs(i, j)
                    count += 1 # 덩어리의 개수이므로, dfs 끝난 후에 1번만 세면 된다.
        return count




if __name__ == '__main__':
    g = graphSolution()
    grid = [
        [1,1,1,1,0],
        [1,1,0,1,0],
        [1,1,0,0,0],
        [0,0,0,0,0]
    ]
    grid2 = [
        [1,1,0,0,0],
        [1,1,0,0,0],
        [0,0,1,0,0],
        [0,0,0,1,1]
    ]
    print(g.numIslands(grid)) # 1
    print(g.numIslands(grid2)) # 3
