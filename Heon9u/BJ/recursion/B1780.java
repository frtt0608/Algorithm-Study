import java.util.*;
import java.io.*;

// 종이의 개수
public class B1780 {
    static int[] paperCnt;
    static int[][] paper;

    public static void addPaperCnt(int num) {
        paperCnt[num+1] += 1;
    }

    public static boolean checkSameNumber(int N, int x, int y) {
        int initNum = paper[x][y];

        for(int i=x; i<x+N; i++) {
            for(int j=y; j<y+N; j++) {
                if(initNum != paper[i][j]) {
                    return false;
                }
            }
        }

        addPaperCnt(initNum);
        return true;
    }

    public static void cuttingPaperArr(int N, int x, int y) {

        if(N == 1) {
            addPaperCnt(paper[x][y]);
            return;
        }

        boolean isSame = checkSameNumber(N, x, y);
        int blockSize = N/3;
        if(!isSame) {
            for(int i=x; i<x+N; i+=blockSize) {
                for(int j=y; j<y+N; j+=blockSize) {
                    cuttingPaperArr(blockSize, i, j);
                }
            }
        }
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
                paper[i][j] = Integer.parseInt(input[j]);
            }
        }

        cuttingPaperArr(N, 0, 0);
        printResultArr();
        br.close();
    }

    public static void printResultArr() {
        for(int cnt: paperCnt) {
            System.out.println(cnt);
        }
    }
}