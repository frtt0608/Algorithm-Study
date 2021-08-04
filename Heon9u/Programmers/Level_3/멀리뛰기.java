public class 멀리뛰기 {
    
    class Solution {
    
        public long solution(int n) {
            long[] table = new long[n+3];
            table[0] = 0;
            table[1] = 1;
            table[2] = 2;
            
            for(int i=3; i<n+1; i++) {
                table[i] = (table[i-1] + table[i-2])%1234567;            
            }
            
            return table[n];
        }
    }   
}
