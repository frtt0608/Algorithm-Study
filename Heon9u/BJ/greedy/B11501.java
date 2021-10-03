import java.util.*;
import java.io.*;

// 주식
// 역으로 생각하기
public class B11501 {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] prices = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                prices[i] = Integer.parseInt(st.nextToken());
            }

            Stock stock = new Stock(N, prices);
            sb.append(stock.getMaxProfit()).append("\n");
        }

        System.out.println(sb.toString());
    }

    static class Stock {
        int N;
        int[] prices;

        Stock(int N, int[] prices) {
            this.N = N;
            this.prices = prices;
        }

        public long getMaxProfit() {
            long profit = 0;
            int maxPrice = prices[N-1];

            for(int i=N-2; i>=0; i--) {
                if(maxPrice < prices[i]) {
                    maxPrice = prices[i];
                } else {
                    profit += maxPrice - prices[i];
                }
            }

            return profit;
        }
    }
}