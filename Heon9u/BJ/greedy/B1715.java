import java.util.*;
import java.io.*;

// 카드 정렬하기
public class B1715 {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int num;

        for(int i=0; i<N; i++) {
            num = Integer.parseInt(br.readLine());
            pq.offer(num);
        }

        CardSort cardSort = new CardSort(N, pq);
        System.out.println(cardSort.combinedCardCount());
    }

    static class CardSort {
        int N;
        int[] num;
        PriorityQueue<Integer> pq;

        CardSort(int N, PriorityQueue<Integer> pq) {
            this.N = N;
            this.pq = pq;
        }

        public int combinedCardCount() {
            int num;
            
            int compareCount = 0;
            while(pq.size() >= 2) {
                num = pq.poll() + pq.poll();

                compareCount += num;
                if(!pq.isEmpty()) {
                    pq.offer(num);
                }
            }

            return compareCount;
        }
    }
}