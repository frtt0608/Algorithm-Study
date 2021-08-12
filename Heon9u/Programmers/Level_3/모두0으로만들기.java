import java.util.*;

public class 모두0으로만들기 {

    class Solution {
        long actionCount;
        long[] sumA;
        List<Integer>[] trees;
        
        public void getMinActionCount(int now, int parent) {
            for(int i=0; i<trees[now].size(); i++) {
                int next = trees[now].get(i);
                // root부터 내려오는 방향
                // next와 parent가 같은 경우는 올라가는 방향(이미 탐색)으로 제외
                if(next != parent) {
                    getMinActionCount(next, now);
                }
            }
            
            sumA[parent] += sumA[now];
            actionCount += Math.abs(sumA[now]);
        }
        
        public void setTreesAndArray(int[] a, int[][] edges) {
            trees = new ArrayList[a.length];
            sumA = new long[a.length];
            for(int i=0; i<a.length; i++) {
                trees[i] = new ArrayList<>();
                sumA[i] = a[i];
            }
    
            for(int[] edge: edges) {
                trees[edge[0]].add(edge[1]);
                trees[edge[1]].add(edge[0]);
            }
        }
        
        public long solution(int[] a, int[][] edges) {
            int sum = Arrays.stream(a).sum();
            
            if(sum == 0) {
                setTreesAndArray(a, edges);
                getMinActionCount(0, 0);
            } else
                actionCount = -1;
            
            return actionCount;
        }
    }
}
