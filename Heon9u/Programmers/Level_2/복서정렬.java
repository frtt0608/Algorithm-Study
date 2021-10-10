import java.util.*;

public class 복서정렬 {
    
    class Solution {

        Boxer[] boxers;
        
        public void initBoxers(int[] weights) {
        for(int i=0; i<weights.length; i++) {
                boxers[i] = new Boxer(i, weights[i]);
            } 
        }
        
        public void setBoxersInfo(int boxerCnt, String[] head2head) {
            int winCnt, totalCnt;
            
            for(int i=0; i<boxerCnt; i++) {
                winCnt = 0;
                totalCnt = 0;
                
                for(int j=0; j<boxerCnt; j++) {
                    if(head2head[i].charAt(j) == 'N') continue;
                    
                    if(head2head[i].charAt(j) == 'W') {
                        winCnt += 1;
                        
                        if(boxers[i].weight < boxers[j].weight) {
                            boxers[i].overWinCnt += 1;
                        }
                    }
                    
                    totalCnt += 1;
                }
                
                if(totalCnt != 0)
                    boxers[i].winRate = winCnt*1000000 / totalCnt;
            }
        }
        
        public int[] sortBoxers() {
            int[] result = new int[boxers.length];
            
            Arrays.sort(boxers, (Boxer boxer1, Boxer boxer2) -> {
                if(boxer1.winRate != boxer2.winRate) return boxer2.winRate - boxer1.winRate > 0 ? 1 : -1;
                if(boxer1.overWinCnt != boxer2.overWinCnt) return boxer2.overWinCnt - boxer1.overWinCnt;
                if(boxer1.weight != boxer2.weight) return boxer2.weight - boxer1.weight;
                
                return boxer1.num - boxer2.num;
            });
            
            for(int i=0; i<boxers.length; i++) {
                result[i] = boxers[i].num + 1;
            }
            
            return result;
        }
        
        public int[] solution(int[] weights, String[] head2head) {
            int boxerCnt = weights.length;
            boxers = new Boxer[boxerCnt];
            
            initBoxers(weights);
            setBoxersInfo(boxerCnt, head2head);
            
            return sortBoxers();
        }
        
        class Boxer {
            int num, weight, overWinCnt;
            float winRate = 0;
            
            Boxer(int num, int weight) {
                this.num = num;
                this.weight = weight;
            }
        }
    }
}