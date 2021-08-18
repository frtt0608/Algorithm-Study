import java.util.*;
import java.io.*;

public class B11727 {
    
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long[] cnt = new long[N+1];
        cnt[0] = 0;
        cnt[1] = 1;
        
        for(int i=2; i<N+1; i++) {
            if(i%2 == 0) cnt[i] = cnt[i-1]*2+1;
            else cnt[i] = cnt[i-1]*2-1;

            cnt[i] %= 10007;
        }

        System.out.println(cnt[N]);
    }
}