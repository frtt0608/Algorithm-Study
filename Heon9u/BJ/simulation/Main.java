import java.util.*;
import java.io.*;

public class Main {
    static final int R = 12;
    static final int C = 6;
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void movedDownToBottom(char[][] map) {
        for(int j=0; j<C; j++) {
            for(int i=R-1; i>=0; i--) {

                if(map[i][j] != '.') {
                    int newI = i;

                    while(newI+1 < R && map[newI+1][j] == '.') {
                        newI += 1;
                    }

                    if(i != newI) {
                        map[newI][j] = map[i][j];
                        map[i][j] = '.';
                    }
                }
            }
        }
    }

    public static void removedSamePuyos(List<Node> saveNodes, char[][] map) {
        for(Node node: saveNodes) {
            map[node.x][node.y] = '.';
        }
    }
    
    public static boolean searchSamePuyo(char[][] map, boolean[][] visited,
                                        int x, int y) {
        boolean flag = false;                        
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(x, y));
        visited[x][y] = true;
        List<Node> saveNodes = new ArrayList<>();
        saveNodes.add(new Node(x, y));

        while(!que.isEmpty()) {
            Node cur = que.poll();
            x = cur.x; 
            y = cur.y;

            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(nx<0 || nx>=R || ny<0 || ny>=C) continue;
                if(visited[nx][ny]) continue;

                if(map[x][y] == map[nx][ny]) {
                    visited[nx][ny] = true;
                    saveNodes.add(new Node(nx, ny));
                    que.offer(new Node(nx, ny));
                }
            }
        }

        if(saveNodes.size() >= 4){
            flag = true;
            removedSamePuyos(saveNodes, map);
        }

        return flag;
    }

    public static boolean checkAroundPuyo(char[][] map) {
        boolean[][] visited = new boolean[R][C];
        boolean flag = false;

        for(int i=R-1; i>=0; i--) {
            for(int j=0; j<C; j++) {
                if(map[i][j] == '.' || visited[i][j]) continue;

                if(searchSamePuyo(map, visited, i, j)) {
                    flag = true;
                }
            }
        }

        return flag;
    }

    public static int getRemovedPuyoCnt(char[][] map) {
        int cnt = 0;
        
        while(checkAroundPuyo(map)) {
            movedDownToBottom(map);
            cnt += 1;
        }

        return cnt;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st;

        char[][] map = new char[R][C];
        for(int i=0; i<R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int destroyedPuyoCnt = getRemovedPuyoCnt(map);
        System.out.println(destroyedPuyoCnt);
    }
}