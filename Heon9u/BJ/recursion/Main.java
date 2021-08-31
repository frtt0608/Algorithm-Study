import java.util.*;
import java.io.*;

public class Main {
    static char[][] map;
    
    public static void drawingStar(int N, int sx, int sy, boolean isBlank) {
        
        
        if(isBlank) {
            for(int i=sx; i<sx+N; i++) {
                for(int j=sy; j<sy+N; j++) {
                    map[i][j] = ' ';  
                }
            }
            return;
        }
        
        if(N == 1) {
            map[sx][sy] = '*';
            return;
        }
        
        int size = N/3;
        int cnt = 0;

        for(int i=sx; i<sx+N; i+=size) {
            for(int j=sy; j<sy+N; j+=size) {
                cnt += 1;
                if(cnt == 5)
                    drawingStar(N/3, i, j, true);
                else
                    drawingStar(N/3, i, j, false);    
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        drawingStar(N, 0, 0, false);

        for(int i=0; i<N; i++) {
            bw.write(map[i]);
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}