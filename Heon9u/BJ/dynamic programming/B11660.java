import java.util.*;
import java.io.*;

// 구간 합 구하기5
public class B11660 {
    static int N;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[][] map = new long[N+1][N+1];
        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<N+1; j++) {
                map[i][j] = Long.parseLong(st.nextToken()) +
                            map[i-1][j] + map[i][j-1] - map[i-1][j-1];
            }
        }

        int x1, y1, x2, y2;
        long result;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            result = map[x2][y2] + map[x1-1][y1-1] - map[x1-1][y2] - map[x2][y1-1];
            sb.append(result+"\n");
        }

        System.out.println(sb.toString());
        br.close();
    }
}