import java.util.*;
import java.io.*;

public class B1992 {
    static StringBuilder sb;
    static int[][] video;

    public static boolean checkSameNumber(int N, int x, int y) {
        int initNum = video[x][y];

        for(int i=x; i<x+N; i++) {
            for(int j=y; j<y+N; j++) {
                if(initNum != video[i][j]) {
                    
                    return false;
                }
            }
        }

        sb.append(initNum);
        return true;
    }

    public static void compressedVideoArr(int N, int x, int y) {

        if(N == 1) {
            // System.out.println(N+": "+x+", "+y);
            sb.append(video[x][y]);
            return;
        }

        boolean isSame = checkSameNumber(N, x, y);
        
        if(!isSame) {
            sb.append("(");
            compressedVideoArr(N/2, x, y);
            compressedVideoArr(N/2, x, y+N/2);
            compressedVideoArr(N/2, x+N/2, y);
            compressedVideoArr(N/2, x+N/2, y+N/2);
            sb.append(")");
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        video = new int[N][N];
        sb = new StringBuilder();

        for(int i=0; i<N; i++) {
            String[] input = br.readLine().split("");
            for(int j=0; j<N; j++) {
                video[i][j] = Integer.parseInt(input[j]);
            }
        }

        compressedVideoArr(N, 0, 0);
        System.out.println(sb.toString());
        br.close();
    }
}