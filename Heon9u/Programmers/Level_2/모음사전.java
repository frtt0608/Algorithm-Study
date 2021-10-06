import java.util.*;

public class 모음사전 {

    class Solution_ver1 {

        public int solution(String word) {
            int answer = word.length();
            int term = 781;
            char[] chars = new char[] {'A', 'E', 'I', 'O', 'U'};
    
            for(int i=0; i<word.length(); i++) {
                answer += term * Arrays.binarySearch(chars, word.charAt(i));
                term = (term - 1) / 5;
            }
    
            return answer;
        }
    }

    class Solution_ver2 {
    
        public int solution(String word) {
            int answer = 0;
            int term = 3905; // 'UUUUU'
            
            for(String s: word.split("")) {
                answer += "AEIOU".indexOf(s) * (term /= 5) + 1;
            }
            
            return answer;
        }
    }
}
