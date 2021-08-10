public class 스티커모으기2_dp {

    class Solution {
        
        public int solution(int sticker[]) {
            int answer = 0;
            int size = sticker.length;
            
            if(size == 1)
                answer = sticker[0];
            else if(size == 2)
                answer = Math.max(sticker[0], sticker[1]);
            else 
                answer = Math.max(getSumStickerNumber(sticker, 0, size-1), 
                                getSumStickerNumber(sticker, 1, size));
            
            return answer;
        }
        
        public int getSumStickerNumber(int[] sticker, int from, int to) {
            int[] maxSum = new int[to];
            maxSum[0] = sticker[from];
            maxSum[1] = Math.max(sticker[from+1], sticker[from]);
            
            int sIdx = 2;
            for(int i=from+2; i<to; i++) {
                maxSum[sIdx] = Math.max(maxSum[sIdx-1], maxSum[sIdx-2]+sticker[i]);
                sIdx += 1;
            }
            
            return Math.max(maxSum[maxSum.length-1], maxSum[maxSum.length-2]);
        }
    }
}
