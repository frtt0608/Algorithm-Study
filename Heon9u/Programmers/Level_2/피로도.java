
// 순열과 조합 - 완전탐색.
public class 피로도 {

    class Solution {
        int answer;
        
        public void searchMaxDungeonCount(int[][] dungeons, boolean[] visited, 
                                        int count, int k) {
            
            answer = Math.max(answer, count);
            
            for(int i=0; i<dungeons.length; i++) {
                if(visited[i]) continue;
                
                if(k >= dungeons[i][0]) {
                    visited[i] = true;
                    searchMaxDungeonCount(dungeons, visited, count+1, k-dungeons[i][1]);
                    visited[i] = false;
                }
            }
        }
        
        public int solution(int k, int[][] dungeons) {
            answer = 0;
            searchMaxDungeonCount(dungeons, new boolean[dungeons.length], 0, k);
            
            return answer;
        }
    }
}
