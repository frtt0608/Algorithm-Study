import java.util.*;

public class 합승택시요금_floyd {

    class Solution {
        final int MAX = 10000000;
        int[][] fareTable;
        
        public void setMinFareTable(int n, int[][] fares) {
            for(int i=1; i<n+1; i++) {
                Arrays.fill(fareTable[i], MAX);
            }
            
            for(int[] fare: fares) {
                fareTable[fare[0]][fare[1]] = fare[2];
                fareTable[fare[1]][fare[0]] = fare[2];
            }
            
            for(int k=1; k<n+1; k++) {
                for(int i=1; i<n+1; i++) {
                    for(int j=1; j<n+1; j++) {
                        fareTable[i][j] = Math.min(fareTable[i][j], 
                                                   fareTable[i][k] + fareTable[k][j]);
                    }
                }
            }
        }
        
        public int getMinFare(int n, int s, int a, int b) {
            int minFare = MAX;
            
            for(int i=1; i<n+1; i++) {
                
                minFare = Math.min(minFare, fareTable[s][i] + fareTable[i][a] + fareTable[i][b]);    
            }
            
            return minFare;
        }
        
        public int solution(int n, int s, int a, int b, int[][] fares) {
            int answer = 0;
            fareTable = new int[n+1][n+1];
            
            setMinFareTable(n, fares);
            fareTable[a][a] = 0;
            fareTable[b][b] = 0;
            fareTable[s][s] = 0;
            
            answer = getMinFare(n, s, a, b);
            
            return answer;
        }
    }
}
