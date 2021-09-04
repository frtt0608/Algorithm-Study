import java.util.*;
import java.io.*;

// Z
public class B1074 {
    static int r, c, order;

    public static void getTargetLocation(int N, int x, int y) {
        if(N == 0) return;

        if(r < x+N && c >= y+N) {
            y += N;
            order += N*N;
        } else if(r >= x+N && c < y+N) {
            x += N;
            order += N*N*2;
        } else if(r >= x+N && c >= y+N) {
            x += N;
            y += N;
            order += N*N*3;
        }

        getTargetLocation(N/2, x, y);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        N = (int) Math.pow(2, N);
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        getTargetLocation(N/2, 0, 0);
        System.out.println(order);
        br.close();
    }
}