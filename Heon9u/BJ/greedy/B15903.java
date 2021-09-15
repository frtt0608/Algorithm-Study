import java.util.*;
import java.io.*;

// 카드합체놀이
// 입력값에서 최소값 또는 최대값을 찾아야하는 경우, 우선순위 큐
public class B15903 {
    
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        String[] cards = br.readLine().split(" ");
        
        CardPlay cardPlay = new CardPlay(n, m, cards);
        cardPlay.offerCardsInPq();
        cardPlay.combineCardNumber();

        System.out.println(cardPlay.getTotalCardNumber());
    }

    static class CardPlay {
        int n, m;
        String[] cards;
        PriorityQueue<Long> pq;

        CardPlay(int n, int m, String[] cards) {
            this.n = n;
            this.m = m;
            this.cards = cards;
        }

        public void offerCardsInPq() {
            pq = new PriorityQueue<>();

            for(String card: cards) {
                pq.offer(Long.valueOf(card));
            }
        }

        public void combineCardNumber() {
            long x, y, z;

            while(m-- > 0) {
                x = pq.poll();
                y = pq.poll();

                z = x + y;
                pq.offer(z);
                pq.offer(z);
            }
        }

        public long getTotalCardNumber() {
            long sum = 0;

            while(!pq.isEmpty()) {
                sum += pq.poll();
            }

            return sum;
        }
    }
}