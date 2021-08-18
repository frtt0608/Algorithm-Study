import java.util.*;

public class GPS {

    class Solution {
        List<Integer>[] edges;
        
        public int getMinErrorCnt(int n, int k, int[] gps_log) {
            int[][] errorTable = new int[k][n+1];
            for(int i=0; i<k; i++)
                Arrays.fill(errorTable[i], 20000000);
            
            errorTable[0][gps_log[0]] = 0;
            
            for(int i=1; i<k; i++) {
                for(int j=1; j<n+1; j++) {
                    errorTable[i][j] = Math.min(errorTable[i][j], errorTable[i-1][j]);
                    
                    for(int next: edges[j]) {
                        errorTable[i][j] = Math.min(errorTable[i][j], errorTable[i-1][next]);
                    }
                    
                    errorTable[i][j] += (j == gps_log[i]) ? 0:1;
                }
            }
            
            return errorTable[k-1][gps_log[k-1]];
        }
        
        public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
            int answer = 0;
            edges = new ArrayList[n+1];
            
            for(int i=1; i<n+1; i++) {
                edges[i] = new ArrayList<>();
            }
            
            for(int[] edge: edge_list) {
                edges[edge[0]].add(edge[1]);
                edges[edge[1]].add(edge[0]);
            }
            
            answer = getMinErrorCnt(n, k, gps_log);
            return answer >= 20000000 ? -1:answer;
        }
    }
}
