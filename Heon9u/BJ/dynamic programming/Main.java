import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] map;

    public static long getRouteCnt() {
        long[][] routeCnt = new long[N][N];
        boolean[][] visited = new boolean[N][N];
        routeCnt[0][0] = 1;
        visited[0][0] = true;

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                int jump = map[i][j];

                if(!visited[i][j] || jump == 0) continue;
                if(i+jump < N) {
                    routeCnt[i+jump][j] += routeCnt[i][j];
                    visited[i+jump][j] = true;
                }
                
                if(j+jump < N) {
                    routeCnt[i][j+jump] += routeCnt[i][j];
                    visited[i][j+jump] = true;
                }
            }
        }

        return routeCnt[N-1][N-1];
    }
    
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(getRouteCnt());
    }
}