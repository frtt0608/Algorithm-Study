import java.util.*;
import java.io.*;

public class Main {
    static int h, w, documentCnt;
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    static boolean[] keys;
    static Queue<Node> que, doors;
    static char[][] map;

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean isWall(int x, int y) {
        return x<0 || x>=h || y<0 || y>=w;
    }

    public static void searchDocumentAndDoor() {
        while(!que.isEmpty()) {
            Node cur = que.poll();
            int x = cur.x;
            int y = cur.y;

            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
    
                if(isWall(nx, ny) || map[nx][ny] == '*') continue;
    
                if(map[nx][ny] >= 'a' && map[nx][ny] <= 'z') {
                    keys[map[nx][ny]-97] = true;
                } else if(map[nx][ny] >= 'A' && map[nx][ny] <= 'Z') {
                    doors.offer(new Node(nx, ny));
                    continue;
                } else if(map[nx][ny] == '$') {
                    documentCnt += 1;
                } 
    
                map[nx][ny] = '*';
                que.offer(new Node(nx, ny));
            }
        }
    }

    public static void checkDoorWithKey() {
        int size = doors.size();

        while(size-- > 0) {
            Node door = doors.poll();

            int doorNum = map[door.x][door.y]-65;
            if(map[door.x][door.y] != '*' && keys[doorNum]) {
                map[door.x][door.y] = '*';
                que.offer(door);
            } else 
                doors.offer(door);
        }
    }

    public static void enterIntoMap() {
        while(!que.isEmpty()) {
            searchDocumentAndDoor();
            checkDoorWithKey();
        }
    }

    public static void offerSideOfMap() {
        
        for(int i=0; i<h; i++) {
            que.offer(new Node(i, -1));
            que.offer(new Node(i, w));
        }

        for(int j=0; j<w; j++) {
            que.offer(new Node(-1, j));
            que.offer(new Node(h, j));
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            documentCnt = 0;
            keys = new boolean[27];
            que = new LinkedList<>();
            doors = new LinkedList<>();

            map = new char[h][w];
            for(int i=0; i<h; i++) {
                map[i] = br.readLine().toCharArray();
            }

            char[] initKey = br.readLine().toCharArray();
            if(initKey[0] != '0') {
                for(char key: initKey) {
                    keys[key-97] = true;
                }
            }
            
            offerSideOfMap();
            enterIntoMap();

            System.out.println(documentCnt);
        }
    }
}