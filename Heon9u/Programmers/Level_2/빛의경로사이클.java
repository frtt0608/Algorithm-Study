import java.util.*;

public class 빛의경로사이클 {

    class Solution {
        int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
        List<Integer> results = new ArrayList<>();
        
        public void totalDirectionLight(String[] grid) {
            int N = grid.length;
            int M = grid[0].length();
            boolean[][][] visited = new boolean[N][M][4];
            
            int curDir = 0;
            int curX = 0;
            int curY = 0;
            int cycleCount = 0;
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    for(int dir=0; dir<4; dir++) {
                        curX = i;
                        curY = j;
                        curDir = dir;
                        cycleCount = 0;
                        
                        while(!visited[curX][curY][curDir]) {
                            
                            visited[curX][curY][curDir] = true;
                            
                            if(grid[curX].charAt(curY) == 'L') curDir = (curDir+1)%4;
                            else if(grid[curX].charAt(curY) == 'R') curDir = (curDir+3)%4;
                            
                            curX += dx[curDir];
                            curY += dy[curDir];
                            
                            if(curX == -1) curX = N-1;
                            else if(curX == N) curX = 0;
                            
                            if(curY == -1) curY = M-1;
                            else if(curY == M) curY = 0;
                            
                            cycleCount += 1;
                        }
                        
                        if(cycleCount != 0) {
                            results.add(cycleCount);
                        }
                    }
                }
            }
        }
        
        public int[] solution(String[] grid) {
            totalDirectionLight(grid);
            Collections.sort(results);
            int[] answer = results.stream().mapToInt(Integer::intValue).toArray();
                
            return answer;
        }
    }
}