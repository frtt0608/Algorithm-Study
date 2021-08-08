public class 가장긴팰린드롬_TopDown {

    // Manacher's Algorithm
    
    class Solution {
        
        public int solution(String s) {
            char[] chars = s.toCharArray();
            
            for(int size=s.length(); size>1; size--) {
                
                for(int start=0; start<s.length()-size+1; start++) {
                    boolean flag = true;
                    
                    for(int i=0; i<size/2; i++) {
                        if(chars[start+i] != chars[start+size-i-1]) {
                            flag = false;
                            break;
                        }
                    }
                    
                    if(flag)
                        return size;
                }
            }
            
            return 1;
        }
    }
}
