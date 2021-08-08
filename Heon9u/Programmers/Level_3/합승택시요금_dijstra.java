import java.util.*;

public class 합승택시요금_dijstra {

    class Solution {
        int answer = Integer.MAX_VALUE;
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
        
        public void getMinFareTable(int start, int[] table) {
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(start, 0));
            Arrays.fill(table, Integer.MAX_VALUE);
            table[start] = 0;
            
            while(!pq.isEmpty()) {
                Node cur = pq.poll();
                
                if(table[cur.route] < cur.fare) continue;
                if(connects[cur.route] == null) continue;
                
                for(Node next: connects[cur.route]) {
                    if(table[next.route] > table[cur.route] + next.fare) {
                        table[next.route] = table[cur.route] + next.fare;
                        pq.offer(new Node(next.route, table[next.route]));
                    }
                }
            }
        }
        
        public void setMinFareTable(int n, int s, int a, int b) {
            int[] startS = new int[n+1];
            int[] startA = new int[n+1];
            int[] startB = new int[n+1];
            
            getMinFareTable(s, startS);
            getMinFareTable(a, startA);
            getMinFareTable(b, startB);
            
            for(int i=1; i<n+1; i++) {
                answer = Math.min(answer, startS[i]+startA[i]+startB[i]);
            }
        }
        
        public int solution(int n, int s, int a, int b, int[][] fares) {
            connects = new ArrayList[n+1];
            
            for(int[] fare: fares) {
                for(int i=0; i<2; i++) {
                    if(connects[fare[i]] == null) {
                        connects[fare[i]] = new ArrayList<>();
                    }
                    int end = i == 0 ? 1:0;
                    
                    connects[fare[i]].add(new Node(fare[end], fare[2]));
                }
            }
            
            setMinFareTable(n, s, a, b);
            
            return answer;
        }
    }
}
