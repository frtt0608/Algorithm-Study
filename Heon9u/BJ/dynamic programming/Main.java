import java.util.*;
import java.io.*;

public class Main {
    // static int N;

    public static int getMinNumber(int N) {
        int[] table = new int[N+1];
        
        for(int i=1; i<N+1; i++) {
            table[i] = i;

            for(int j=1; j*j<i+1; j++) {
                table[i] = Math.min(table[i], table[i-j*j]+1);
            }
        }

        return table[N];
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        System.out.println(getMinNumber(N));
    }
}