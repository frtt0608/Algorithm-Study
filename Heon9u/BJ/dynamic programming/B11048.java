import java.util.*;
import java.io.*;

public class B11048 {

    public static void getTotalCase(int[][] map, int N, int M) {
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<M+1; j++) {
                int max = Math.max(map[i-1][j], map[i][j-1]);
                max = Math.max(max, map[i-1][j-1]);

                map[i][j] += max;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // int N = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N+1][M+1];
        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<M+1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        getTotalCase(map, N, M);
        System.out.println(map[N][M]);
    }
}