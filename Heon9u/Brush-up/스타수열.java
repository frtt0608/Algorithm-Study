import java.util.*;

public class 스타수열 {

    class Solution {
        Map<Integer, Integer> mapOfNum;
        List<Node> nodes;
        
        public void setASC_MaxCountNodes(int[] nums) {
            for(int num: nums) {
                mapOfNum.put(num, mapOfNum.getOrDefault(num, 0)+1);
            }
            
            for(int key: mapOfNum.keySet()) {
                nodes.add(new Node(key, mapOfNum.get(key)));
            }
            
            nodes.sort(new Comparator<Node>() {
                @Override
                public int compare(Node n1, Node n2) {
                    return n2.cnt - n1.cnt;
                }
            });
        }
        
        public int getLongestStarArray(int[] a) {
            int starArrayLeng = 0;
            
            for(Node node: nodes) {
                if(starArrayLeng >= node.cnt*2)
                    break;
                
                int num = node.num;
                int leng = 0;
            
                for(int i=0; i<a.length-1; i++) {
                    if((a[i] != num && a[i+1] == num) || (a[i] == num && a[i+1] != num)) {
                        leng += 2;
                        i += 1;
                    }
                }
                
                starArrayLeng = Math.max(starArrayLeng, leng);
            }
            
            return starArrayLeng;
        }
        
        public int solution(int[] a) {
            int answer = 0;
            mapOfNum = new HashMap<>();
            nodes = new ArrayList<>();
            setASC_MaxCountNodes(a);
            answer = getLongestStarArray(a);
    
            return answer;
        }
        
        class Node {
            int num, cnt;
            
            Node(int num, int cnt) {
                this.num = num;
                this.cnt = cnt;
            }
        }
    }
}
