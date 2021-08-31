import java.util.*;
import java.io.*;

// 하노이의 탑
public class B11729 {
    static int cnt;
    static StringBuilder sb;

    public static void setHanoiTop(int N, int from, int to) {
        if(N == 0) return;

        cnt += 1;
        setHanoiTop(N-1, from, 6-from-to);
        sb.append(from+" "+to).append("\n");
        setHanoiTop(N-1, 6-from-to, to);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        setHanoiTop(N, 1, 3);
        System.out.println(cnt);
        System.out.println(sb.toString());

        br.close();
    }
}