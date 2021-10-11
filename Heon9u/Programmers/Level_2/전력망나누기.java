public class 전력망나누기 {

    class MySolution {
    
        public boolean[][] setTreeArray(int idx, int n, int[][] wires) {
            boolean[][] trees = new boolean[n+1][n+1];
            
            for(int i=0; i<wires.length; i++) {
                if(i == idx) continue;
                
                trees[wires[i][0]][wires[i][1]] = true;
                trees[wires[i][1]][wires[i][0]] = true;
            }
            
            return trees;
        }
        
        public int getTowerCount(int idx, boolean[][] trees, boolean[] visited) {
            int towerCnt = 1;
            visited[idx] = true;
            
            for(int i=0; i<trees.length; i++) {
                if(visited[i]) continue;
                if(trees[idx][i]) {
                    towerCnt += getTowerCount(i, trees, visited);
                }
            }
            
            return towerCnt;
        }
        
        public int getDifferNetwork(int n, boolean[][] trees) {
            boolean[] visited = new boolean[n+1];
            int differCount = 0;
            
            for(int i=1; i<n+1; i++) {
                if(visited[i]) break;
                differCount = getTowerCount(i, trees, visited);
                differCount = Math.abs(differCount - (n - differCount));
            }
            
            return differCount;
        }
        
        public int solution(int n, int[][] wires) {
            int answer = 101;
            boolean[][] trees;
            
            for(int i=0; i<n; i++) {
                trees = setTreeArray(i, n, wires);
                answer = Math.min(answer, getDifferNetwork(n, trees));
                
                if(answer == 0)
                    break;
            }
            
            return answer;
        }
    }

    class AnotherSolution {
        int N, minCount;
        boolean[] visited;
        boolean[][] map;
        
        public void setGlobalVariables(int n, int[][] wires) {
            N = n;
            minCount = n;
            visited = new boolean[n+1];
            map = new boolean[n+1][n+1];
            
            for(int[] wire: wires) {
                int r = wire[0];
                int c = wire[1];
                
                map[r][c] = map[c][r] = true;
            }
        }
        
        public int getConnectTower(int idx) {
            visited[idx] = true;
            int count = 1;
            
            for(int i=1; i<N+1; i++) {
                if(!visited[i] && map[idx][i]) {
                    count += getConnectTower(i);
                }
            }
            
            minCount = Math.min(minCount, Math.abs(count - (N - count)));
            return count;
        }
        
        public int solution(int n, int[][] wires) {
            setGlobalVariables(n, wires);
            getConnectTower(1);
            
            return minCount;
        }
    }
}
