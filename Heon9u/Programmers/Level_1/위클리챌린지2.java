import java.util.*;

public class 위클리챌린지2 {

    class Solution {
        
        public String getAvgWithGrade(int[][] scores) {
            String answer = "";
            int size = scores.length;
            
            for(int i=0; i<size; i++) {
                double avg = 0;
                int max = 0;
                int min = 101;
                int sum = 0;
                int sameCnt = 0;
                int scoreCnt = size;
                
                for(int j=0; j<size; j++) {
                    sum += scores[j][i];
                    if(scores[j][i] == scores[i][i]) {
                        sameCnt += 1;
                    }
                    
                    max = Math.max(max, scores[j][i]);
                    min = Math.min(min, scores[j][i]);
                }
                
                if((scores[i][i] == min && sameCnt == 1) ||
                        (scores[i][i] == max && sameCnt == 1)) {
                    
                    sum -= scores[i][i];
                    scoreCnt -= 1;
                    
                }
                
                avg = sum/scoreCnt;
                answer += (avg>=90 ? "A": avg>=80 ? "B": avg>=70 ? "C": avg>=50 ? "D":"F");
            }
            
            return answer;
        }
        
        public String solution(int[][] scores) {
            String answer = "";
            answer = getAvgWithGrade(scores);
            
            return answer;
        }
    }
}