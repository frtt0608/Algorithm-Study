public class 거스름돈_dp {
    
    class Solution {
        int answer;
        final int MODE = 1000000007;
    
        public int getTotalCase(int n, int[] coins) {
            int[] counts = new int[n+1];
            counts[0] = 1;
            
            for(int coin: coins) {
                for(int money=coin; money<n+1; money++) {
                    counts[money] += counts[money-coin];
                    counts[money] %= MODE;
                }
            }
            
            return counts[n];
        }
        
        public int solution(int n, int[] coins) {
            answer = getTotalCase(n, coins);
            
            return answer;
        }
    }
}
