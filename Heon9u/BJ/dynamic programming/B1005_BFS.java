import java.util.*;
import java.io.*;

public class B1005_BFS {
    static int N, W;
    static int[] nextCnt, timer, minTable;
    static List<Integer>[] nextOrders;

    public static void getStartPoint(Queue<Integer> que) {
        for(int i=1; i<N+1; i++) {
            if(nextCnt[i] == 0) {
                que.offer(i);
                minTable[i] = timer[i];
            }
        }
    }

    public static void getMinTime() {
        Arrays.fill(minTable, -1);
        Queue<Integer> que = new LinkedList<>();
        getStartPoint(que);

        while(!que.isEmpty()) {
            int cur = que.poll();
            if(cur == W) return;

            for(int next: nextOrders[cur]) {
                nextCnt[next] -= 1;
                minTable[next] = Math.max(minTable[next], minTable[cur] + timer[next]);
                if(nextCnt[next] == 0)
                    que.offer(next);
            }
        }
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
            nextCnt = new int[N+1];

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

                nextOrders[cur].add(next);
                nextCnt[next] += 1;
            }

            W = Integer.parseInt(br.readLine());
            getMinTime();

            System.out.println(minTable[W]);
        }
    }
}