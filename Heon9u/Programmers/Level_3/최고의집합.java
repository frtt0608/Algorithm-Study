public class 최고의집합 {

    class Solution {
        
        public int[] solution(int n, int s) {
            int[] answer = new int[n];
            
            if(s < n)
                return new int[]{-1};
                
            for(int i=0; i<n; i++) {
                answer[i] = s/n;
            }
            
            if(s%n != 0) {
                for(int i=0; i<s%n; i++) {
                    answer[n-1-i] += 1;
                }
            }
            
            return answer;
        }
    }
}
