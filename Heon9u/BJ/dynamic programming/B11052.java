import java.util.*;
import java.io.*;

// Combination table
public class B11052 {
    static int N;
    static int[] cards;

    public static int getMaxPrice() {
        int[] table = new int[N+1];

        for(int i=1; i<N+1; i++) {
            for(int j=1; j<i+1; j++) {
                table[i] = Math.max(table[i], table[i-j]+cards[j]);
            }
        }

        return table[N];
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        cards = new int[N+1];

        for(int i=1; i<N+1; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(getMaxPrice());
    }
}