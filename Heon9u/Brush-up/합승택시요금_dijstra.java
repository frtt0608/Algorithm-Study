import java.util.*;

public class 합승택시요금_dijstra {

    class Solution {
        int[][] fareTable;
        List<Node>[] connects;
        
        class Node implements Comparable<Node> {
            int route, fare;
            
            Node(int route, int fare) {
                this.route = route;
                this.fare = fare;
            }
            
            @Override
            public int compareTo(Node node) {
                return this.fare - node.fare;
            }
        }
        
        public void getMinFareRoute(int start, int[] fareTable) {
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(start, 0));
            Arrays.fill(fareTable, 100000000);
            fareTable[start] = 0;
            
            while(!pq.isEmpty()) {
                Node cur = pq.poll();
                
                if(connects[cur.route].size() == 0 || 
                        fareTable[cur.route] < cur.fare) continue;
                
                for(Node next: connects[cur.route]) {
                    if(fareTable[next.route] > cur.fare + next.fare) {
                        fareTable[next.route] = cur.fare + next.fare;
                        pq.offer(new Node(next.route, fareTable[next.route]));
                    }
                }
            }
        }
        
        public void setMinFareTable(int n, int s, int a, int b) {
            fareTable = new int[3][n+1];
            getMinFareRoute(s, fareTable[0]);
            getMinFareRoute(a, fareTable[1]);
            getMinFareRoute(b, fareTable[2]);
        }
        
        public int getMinFareGotoAB(int n, int s, int a, int b) {
            int minFare = Integer.MAX_VALUE;
            for(int i=1; i<n+1; i++) {
                minFare = Math.min(minFare, fareTable[0][i] + fareTable[1][i] + fareTable[2][i]);
            }
            
            return minFare;
        }
        
        public int solution(int n, int s, int a, int b, int[][] fares) {
            int answer = 0;
            connects = new ArrayList[n+1];
            
            for(int i=1; i<n+1; i++) {
                connects[i] = new ArrayList<>();
            }
            
            for(int[] fare: fares) {
                connects[fare[0]].add(new Node(fare[1], fare[2]));
                connects[fare[1]].add(new Node(fare[0], fare[2]));
            }
            
            setMinFareTable(n, s, a, b);
            answer = getMinFareGotoAB(n, s, a, b);
            
            return answer;
        }
    }
}