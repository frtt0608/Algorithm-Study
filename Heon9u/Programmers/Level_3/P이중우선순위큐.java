import java.util.*;

public class P이중우선순위큐 {
    class Solution {
        PriorityQueue<Integer>[] pq;
        
        public int[] solution(String[] operations) {
            int[] answer = new int[2];
            int index = 0;
            pq = new PriorityQueue[2];
            pq[0] = new PriorityQueue<>(); // 오름
            pq[1] = new PriorityQueue<>(Collections.reverseOrder()); // 내림
            
            for(String operation: operations) {
                char cmd = operation.charAt(0);
                int num = Integer.parseInt(operation.substring(2));
                
                if(cmd == 'I') {
                    pq[index].offer(num);
                } else {
                    if(pq[index].isEmpty()) continue;
                    
                    if(num < 0) {
                        pq[0].addAll(pq[1]);
                        pq[1].clear();
                        index = 0;
                        
                    } else {
                        pq[1].addAll(pq[0]);
                        pq[0].clear();
                        index = 1;
                    }

                    pq[index].poll();
                }
            }
            
            if(!pq[index].isEmpty()) {
                if(index == 0) {
                    answer[1] = pq[index].peek();
                    pq[1].addAll(pq[0]);
                    answer[0] = pq[1].peek();
                    
                } else {
                    answer[0] = pq[index].peek();
                    pq[0].addAll(pq[1]);
                    answer[1] = pq[0].peek();
                }
            }
            
            return answer;
        }
    }
}