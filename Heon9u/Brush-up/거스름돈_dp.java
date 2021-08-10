public class 거스름돈_dp {

    class Solution {
        
        final int MODE = 1000000007;
        
        public int getTotalCase(int n, int[] coins) {
            int[] table = new int[n+1];
            table[0] = 1;
            
            for(int coin: coins) {
                for(int money=coin; money<n+1; money++) {
                    table[money] += table[money-coin];
                }
            }
            
            return table[n]%MODE;
        }
        
        public int solution(int n, int[] money) {
            int answer = getTotalCase(n, money);
            return answer;
        }
    }
}
