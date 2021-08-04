import java.util.*;

public class 야근지수 {

    class Solution {
        public long solution(int n, int[] works) {
            long answer = 0;
            
            PriorityQueue<Integer> pq = new PriorityQueue<>((num1, num2) -> num2.compareTo(num1));
            for(int work: works) {
                pq.offer(work);
            }
            
            int count = 0;
            while(count < n && !pq.isEmpty()) {
                int num = pq.poll();
                
                if(num-1 > 0) {
                    pq.offer(num-1);
                }
                
                count += 1;
            }
            
            while(!pq.isEmpty()) {
                answer += Math.pow(pq.poll(), 2);
            }
            
            return answer;
        }
    }
}
