import java.util.*;
import java.io.*;

public class B9465 {
    static int N;

    public static int detachedStickers(int[][] stickers) {
        int[][] table = new int[2][N];
        table[0][0] = stickers[0][0];
        table[1][0] = stickers[1][0];
        
        for(int j=1; j<N; j++) {
            table[0][j] = Math.max(table[0][j-1], table[1][j-1]+stickers[0][j]);
            table[1][j] = Math.max(table[1][j-1], table[0][j-1]+stickers[1][j]);
        }

        return Math.max(table[0][N-1], table[1][N-1]);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++) {
            N = Integer.parseInt(br.readLine());
            int[][] stickers = new int[2][N];
            for(int i=0; i<2; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    stickers[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append(detachedStickers(stickers)).append("\n");
        }

        System.out.println(sb.toString());
    }
}