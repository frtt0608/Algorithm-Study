public class 가장긴팰린드롬_odd_even {

    // Manacher's Algorithm
    
    class Solution {
        
        public int getLongestPelindrome(int left, int right, char[] chars) {
            
            while(0 <= left && chars.length > right && chars[left] == chars[right]) {
                left -= 1;
                right += 1;
            }
            
            return right - left - 1;
        }
        
        public int solution(String s) {
            int answer = 1;
            char[] chars = s.toCharArray();
            
            for(int i=0; i<s.length()-1; i++) {
                answer = Math.max(answer, getLongestPelindrome(i, i, chars));
                answer = Math.max(answer, getLongestPelindrome(i, i+1, chars));
            }
            
            return answer;
        }
    }
}
