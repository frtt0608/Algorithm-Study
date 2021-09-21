import java.util.*;
import java.io.*;

// 거스름돈
public class B14916 {

    public static int getCoinCount(int N) {
        if(N==1 || N==3) return -1;

        int cnt = N/5 + N%5/2;

        if(N%5%2 == 1) {
            cnt += 2;
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        System.out.println(getCoinCount(N));
    }
}