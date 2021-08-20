import java.util.*;
import java.io.*;

public class B1005_DFS {
    static int N;
    static int[] timer, minTable;
    static List<Integer>[] nextOrders;

    public static int getMinTime(int cur) {
        if(minTable[cur] != -1) return minTable[cur];

        int maxTime = 0;
        for(int next: nextOrders[cur]) {
            maxTime = Math.max(maxTime, getMinTime(next));
        }

        return minTable[cur] = maxTime + timer[cur];
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            
            timer = new int[N+1];
            minTable = new int[N+1];
            Arrays.fill(minTable, -1);

            st = new StringTokenizer(br.readLine());
            for(int i=1; i<N+1; i++) {
                timer[i] = Integer.parseInt(st.nextToken());
            }

            nextOrders = new ArrayList[N+1];
            for(int i=1; i<N+1; i++) {
                nextOrders[i] = new ArrayList<>();
            }
            
            for(int i=0; i<K; i++) {
                st = new StringTokenizer(br.readLine());
                int cur = Integer.parseInt(st.nextToken());
                int next = Integer.parseInt(st.nextToken());

                nextOrders[next].add(cur);
            }

            int W = Integer.parseInt(br.readLine());
            getMinTime(W);

            System.out.println(minTable[W]);
        }
    }
}