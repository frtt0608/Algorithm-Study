import java.util.*;
import java.io.*;

public class B2133 {

    public static int getTotalCase(int N) {
        int[] table = new int[N+1];
        table[2] = 3;
        
        for(int i=4; i<N+1; i+=2) {
            table[i] = table[i-2]*3;

            for(int j=i-4; j>=2; j-=2) {
                table[i] += table[j]*2;
            }

            table[i] += 2;
        }

        return table[N];
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int count = 0;

        if(N%2 == 0) {
            count = getTotalCase(N);
        }

        System.out.println(count);
    }
}