import java.util.*;

public class 다단계칫솔판매 {

    class Solution {
        Map<String, String> enrollStruct;
        Map<String, Integer> amounts;
        
        public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
            int[] answer = new int[enroll.length];
            enrollStruct = new HashMap<>();
            amounts = new HashMap<>();
            amounts.put("-", 0);
            
            for(int i=0; i<enroll.length; i++) {
                enrollStruct.put(enroll[i], referral[i]);
                amounts.put(enroll[i], 0);
            }
            
            for(int i=0; i<seller.length; i++) {
                String cur = seller[i];
                int price = amount[i]*100;
                String next = "";
                int nextPrice = 0;
                
                amounts.put(cur, amounts.get(cur)+price);
                
                while(!cur.equals("-") && price > 0) {
                    nextPrice = (int) Math.floor(price*(0.1));
                    next = enrollStruct.get(cur);
                    
                    amounts.put(cur, amounts.get(cur)-nextPrice);
                    amounts.put(next, amounts.get(next)+nextPrice);
                    
                    cur = next;
                    price = nextPrice;
                }
            }
            
            for(int i=0; i<enroll.length; i++) {
                answer[i] = amounts.get(enroll[i]);
            }
            
            return answer;
        }
    }
}
