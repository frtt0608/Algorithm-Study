import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] paper = new int[N][N];
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        CuttingPaper cuttingPaper = new CuttingPaper(N, paper);
        cuttingPaper.cuttingPaperWithColor(paper, N, 0, 0);
        cuttingPaper.printPaperCnt();

        br.close();
    }
}

class CuttingPaper {
    int N;
    int[] paperCnt;
    int[][] paper;

    CuttingPaper(int N, int[][] paper) {
        this.N = N;
        this.paper = paper;
        this.paperCnt = new int[2];
    }

    public void cuttingPaperWithColor(int[][] paper, int N, int x, int y) {

        if(N == 1) {
            paperCnt[paper[x][y]] += 1;
            return;
        }

        int initColor = paper[x][y];
        for(int i=x; i<x+N; i++) {
            for(int j=y; j<y+N; j++) {
                if(initColor != paper[i][j]) {
                    cuttingPaperWithColor(paper, N/2, x, y);
                    cuttingPaperWithColor(paper, N/2, x, y+N/2);
                    cuttingPaperWithColor(paper, N/2, x+N/2, y);
                    cuttingPaperWithColor(paper, N/2, x+N/2, y+N/2);
                    return;
                }
            }
        }

        paperCnt[initColor] += 1;
    }

    public void printPaperCnt() {
        System.out.println(paperCnt[0]+"\n"+paperCnt[1]);
    }
}