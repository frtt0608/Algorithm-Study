import java.util.*;
import java.io.*;

// Combination table
public class B1010 {
    static final int MAX = 30;
    static int N, M;
    static int[][] table;

    public static void setCombinationTable() {
        table = new int[MAX+1][MAX+1];
        table[1][0] = 1;
        table[1][1] = 1;
        
        for(int i=2; i<MAX+1; i++) {
            table[i][0] = 1;
            table[i][i] = 1;
            for(int j=1; j<(i+1)/2+1; j++) {
                table[i][j] = table[i-1][j] + table[i-1][j-1];
                table[i][i-j] = table[i][j];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        setCombinationTable();
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<T+1; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            System.out.println(table[M][N]);
        }
    }
}