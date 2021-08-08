import java.util.*;

public class 합승택시요금_floyd {

    class Solution {
        int answer = Integer.MAX_VALUE;
        int[][] fareTable, minFare;
        List<Integer>[] connects;
        
        class Node {
            int s, fare;
            
            Node(int s, int fare) {
                this.s = s;
                this.fare = fare;
            }
        }
        
        public void getFloydWareshall(int n) {
            for(int k=1; k<n+1; k++) {
                for(int s=1; s<n+1; s++) {
                    for(int e=1; e<n+1; e++) {
                        minFare[s][e] = Math.min(minFare[s][k] + minFare[k][e], minFare[s][e]);
                    }
                }
            }
        }
        
        public void getMinFare(int n, int s, int a, int b) {
            
            for(int k=1; k<n+1; k++) {
                answer = Math.min(answer, minFare[s][k]+minFare[k][a]+minFare[k][b]);
            }
        }
        
        public int solution(int n, int s, int a, int b, int[][] fares) {
            minFare = new int[n+1][n+1];
            
            for(int[] fare: fares) {
                minFare[fare[0]][fare[1]] = fare[2];
                minFare[fare[1]][fare[0]] = fare[2];
            }
            
            for(int i=1; i<n+1; i++) {
                for(int j=1; j<n+1; j++) {
                    if(i == j) continue;
                    
                    if(minFare[i][j] == 0) {
                        minFare[i][j] = 10000000;
                    }
                }
            }
            
            getFloydWareshall(n);
            getMinFare(n, s, a, b);
            
            return answer;
        }
    }
}
