import java.util.*;
import java.io.*;

// Combination table
public class B11057 {
    static int N;

    public static long getASCNumber() {
        long[][] table = new long[N+1][10];
    
        for(int i=1; i<N+1; i++) {
            table[i][0] = 1;
            for(int j=1; j<10; j++) {
                table[i][j] = table[i-1][j] + table[i][j-1];
                table[i][j] %= 10007;
            }
        }

        return Arrays.stream(table[N]).sum();
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        System.out.println(getASCNumber()%10007);
    }
}