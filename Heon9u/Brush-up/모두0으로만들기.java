import java.util.*;

public class 모두0으로만들기 {

    class Solution {
        long answer;
        long[] weights;
        List<Integer>[] trees;
        
        public void setTreeList(int[] a, int[][] edges) {
            trees = new ArrayList[a.length];
            weights = new long[a.length];
            
            for(int i=0; i<a.length; i++) {
                trees[i] = new ArrayList<>();
                weights[i] = a[i];
            }
            
            for(int[] edge: edges) {
                trees[edge[0]].add(edge[1]);
                trees[edge[1]].add(edge[0]);
            }
        }
        
        public void makeNodeToZero(int now, int parent) {
            
            for(int i=0; i<trees[now].size(); i++) {
                int next = trees[now].get(i);
                
                if(next != parent) {
                    makeNodeToZero(next, now);
                }
            }
            
            weights[parent] += weights[now];
            answer += Math.abs(weights[now]);
        }
        
        public long solution(int[] a, int[][] edges) {
            
            if(Arrays.stream(a).sum() != 0) {
                answer = - 1;
            } else {
                setTreeList(a, edges);
                makeNodeToZero(0, 0);
            }
            
            return answer;
        }
    }
}
