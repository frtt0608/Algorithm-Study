import java.util.*;
import java.io.*;

public class Main {
    static int[] paperCnt;
    static int[][] paper;

    public static void addPaperCnt(int[] cnt) {
        for(int i=0; i<3; i++) {
            paperCnt[i] += cnt[i];
        }
    }

    public static int cuttingPaperArr(int N, int x, int y) {

        if(N == 1) return paper[x][y];

        int[] tempCnt = new int[3];
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                int nNum = cuttingPaperArr(N/3, x+i*N/3, y+j*N/3);
                if(nNum != -1) tempCnt[nNum] += 1;
            }
        }

        for(int i=0; i<3; i++) {
            if(tempCnt[i] == 9) return i;
        }

        addPaperCnt(tempCnt);
        return -1;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        paper = new int[N][N];
        paperCnt = new int[3];

        for(int i=0; i<N; i++) {
            String[] input = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                paper[i][j] = Integer.parseInt(input[j]) + 1;
            }
        }

        int num = cuttingPaperArr(N, 0, 0);
        if(num != -1) paperCnt[num] += 1;

        printResultArr();
        br.close();
    }

    public static void printResultArr() {
        for(int cnt: paperCnt) {
            System.out.println(cnt);
        }
    }
}