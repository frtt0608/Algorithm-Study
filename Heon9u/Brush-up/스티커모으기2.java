public class 스티커모으기2 {

    class Solution {
    
        public int getSumDetachedStickerNumber(int[] sticker, int from, int to) {
            int[] maxSum = new int[to];
            int msi = 2;
            maxSum[0] = sticker[from];
            maxSum[1] = Math.max(sticker[from], sticker[from+1]);
            
            for(int i=from+2; i<to; i++) {
                maxSum[msi] = Math.max(maxSum[msi-1], maxSum[msi-2] + sticker[i]);
                msi += 1;
            }
            
            return Math.max(maxSum[to-1], maxSum[to-2]);
        }
        
        public int solution(int[] sticker) {
            int answer = 0;
            int size = sticker.length;
            
            if(size == 1) {
                answer = sticker[0];
            } else if(size == 2) {
                answer = Math.max(sticker[0], sticker[1]);
            } else {
                answer = Math.max(getSumDetachedStickerNumber(sticker, 0, size-1),
                                    getSumDetachedStickerNumber(sticker, 1, size));
            }
            
            return answer;
        }
    }
}
