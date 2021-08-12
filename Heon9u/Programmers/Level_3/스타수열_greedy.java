import java.util.*;

public class 스타수열_greedy {

    class Solution {
        Map<Integer, Integer> numCounts;
        List<Node> numsList;
        
        class Node {
            int num, cnt;
            
            Node(int num, int cnt) {
                this.num = num;
                this.cnt = cnt;
            }
        }
        
        public void setNumCounts(int[] nums) {
            numCounts = new HashMap<>();
            numsList = new ArrayList<>();
            
            for(int num: nums) {
                numCounts.put(num, numCounts.getOrDefault(num, 0)+1);
            }
            
            for(int num: numCounts.keySet()) {
                numsList.add(new Node(num, numCounts.get(num)));
            }
            
            numsList.sort(new Comparator<Node>() {
                @Override
                public int compare(Node node1, Node node2) {
                    return node2.cnt - node1.cnt;
                }
            });
        }
        
        public int getMaxStarArrayLength(int[] a) {
            int answer = 0;
            
            for(Node node: numsList) {
                if(answer >= node.cnt*2)
                    break;
                
                int num = node.num;
                int leng = 0;
                
                for(int i=0; i<a.length-1; i++) {
                    if((a[i] == num && a[i+1] != num) ||
                            (a[i] != num && a[i+1] == num)) {
                        leng += 2;
                        i += 1;
                    }
                }
                
                answer = Math.max(answer, leng);
            }
            
            return answer;
        }
        
        
        public int solution(int[] a) {
            setNumCounts(a);
            int answer = getMaxStarArrayLength(a);
            
            return answer;
        }
    }
}
