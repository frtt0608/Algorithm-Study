import java.util.*;

public class GPS_dp {

    class Solution {
        final int MAX = 20000000;
        int answer;
        List<Integer>[] edges;
        
        public int getMinErrorCnt(int[] gps_log, int n, int k) {
            int[][] errorCnt = new int[k][n+1];
            
            for(int i=0; i<k; i++) {
                Arrays.fill(errorCnt[i], MAX);
            }
            
            errorCnt[0][gps_log[0]] = 0;
            
            // i: 경로 횟수, j: 다음 도착지
            // next: 도착지에 연결된 노드
            for(int i=1; i<k; i++) {
                for(int j=1; j<n+1; j++) {
                    errorCnt[i][j] = Math.min(errorCnt[i][j], errorCnt[i-1][j]);
                    
                    for(int next: edges[j]) {
                        errorCnt[i][j] = Math.min(errorCnt[i][j], errorCnt[i-1][next]);
                    }
                    
                    errorCnt[i][j] += gps_log[i] == j ? 0:1;
                }
            }
            
            return errorCnt[k-1][gps_log[k-1]];
        }
        
        public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
            int answer = MAX;
            edges = new ArrayList[n+1];
            
            for(int i=1; i<n+1; i++)
                edges[i] = new ArrayList<>();
            
            for(int[] edge: edge_list) {
                edges[edge[0]].add(edge[1]);
                edges[edge[1]].add(edge[0]);
            }
            
            answer = getMinErrorCnt(gps_log, n, k);
            
            if(answer == MAX)
                answer = -1;
            
            return answer;
        }
    }
}
