public class Nqueen {
    
    class Solution {
    
        public boolean checkConditionToSetQueen(int[] table, 
                                                int col) {
            
            for(int i=0; i<col; i++) {
                if(table[i] == table[col] || (Math.abs(i-col) == Math.abs(table[i]-table[col]))) 
                    return false;
            }
            
            return true;
        }
        
        public int setTableWithQueen(int[] table,
                                      int n, int col) {
            int sum = 0;
            
            if(col == n) return 1;
            
            for(int row=0; row<n; row++) {
                table[col] = row;
                
                if(checkConditionToSetQueen(table, col)) {
                    sum += setTableWithQueen(table, n, col+1);
                }
            }
            
            return sum;
        }
        
        public int solution(int n) {
            int answer = setTableWithQueen(new int[n], n, 0);
            
            return answer;
        }
    }
}
