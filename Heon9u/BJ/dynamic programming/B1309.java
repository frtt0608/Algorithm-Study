import java.util.*;
import java.io.*;

public class B1309 {

    public static int getTotalCase_lionPutInCage(int N) {
        final int MODE = 9901;
        int[] cage = new int[N+1];
        cage[1] = 2;

        for(int i=2; i<N+1; i++) {
            cage[i] = cage[i-1]*2 + cage[i-2]*1 + 2;
            cage[i] %= MODE;
        }

        return (cage[N]+1)%MODE;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        System.out.println(getTotalCase_lionPutInCage(N));
    }
}