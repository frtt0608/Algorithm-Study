import java.util.*;

public class 프렌즈사천성 {

    class Solution {
        int m, n;
        String answer = "IMPOSSIBLE";
        int[] dx={1,0,-1,0}, dy={0,1,0,-1};
        char[][] boards;
        List<Character> tiles;
        Map<Character, List<Node>> mapOfTiles;
        boolean isMatching = false;
        
        public void getKindsOfTiles(String[] board) {
            
            
            for(int i=0; i<m; i++) {
                boards[i] = board[i].toCharArray();
                
                for(int j=0; j<n; j++) {
                    if(boards[i][j] >= 'A' && boards[i][j] <= 'Z') {
                        
                        List<Node> tileList = new ArrayList<>();
                        if(mapOfTiles.containsKey(boards[i][j])) {
                            tileList = mapOfTiles.get(boards[i][j]);
                        } else {
                            tiles.add(boards[i][j]);
                        }
                        
                        tileList.add(new Node(boards[i][j], i, j));
                        mapOfTiles.put(boards[i][j], tileList);
                    }
                }
            }
        }    
        
        public void getResultSichuan(String result, boolean[] visited) {
            
            if(isMatching) return;
    
            if(result.length() == tiles.size()) {
                answer = result;
                isMatching = true;
                return;
            }
            
            Node start, end;
            for(int i=0; i<tiles.size(); i++) {
                if(visited[i]) continue;
                if(isMatching) return;
                
                start = mapOfTiles.get(tiles.get(i)).get(0);
                end = mapOfTiles.get(tiles.get(i)).get(1);
                if(searchSameCard(start, end)) {
                    visited[i] = true;
                    boards[start.x][start.y] = '.';
                    boards[end.x][end.y] = '.';
                    getResultSichuan(result+start.tile, visited);
                }
            }
        }
        
        public boolean isWall(int x, int y) {
            return x<0 || x>=m || y<0 || y>=n;
        }
        
        public boolean searchSameCard(Node start, Node end) {
            Queue<Node> que = new LinkedList<>();
            que.offer(new Node(start.x, start.y, -1, 0));
            
            while(!que.isEmpty()) {
                Node cur = que.poll();
                
                for(int dir=0; dir<4; dir++) {
                    if(cur.dir != -1 && Math.abs(cur.dir - dir) == 2) continue;
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];
                    
                    if(isWall(nx, ny)) continue;
                    int nextCnt = (cur.dir == dir || cur.dir == -1) ? cur.cnt : cur.cnt+1;
                    
                    if(nextCnt <= 1) {
                        if(boards[nx][ny] == '.') {
                            que.offer(new Node(nx, ny, dir, nextCnt));
                        } else if(nx == end.x && ny == end.y) {
                            return true;
                        }
                    }
                }
            }
            
            return false;
        }
        
        public String solution(int m, int n, String[] board) {
            this.m = m;
            this.n = n;
            boards = new char[m][n];
            tiles = new ArrayList<>();
            mapOfTiles = new HashMap<>();
            
            getKindsOfTiles(board);
            Collections.sort(tiles);
            getResultSichuan("", new boolean[tiles.size()]);
            
            return answer;
        }
        
        class Node {
            char tile;
            int x, y, dir;
            int cnt;
            
            Node(char tile, int x, int y) {
                this.tile = tile;
                this.x = x;
                this.y = y;
            }
            
            Node(int x, int y, int dir, int cnt) {
                this.x = x;
                this.y = y;
                this.dir = dir;
                this.cnt = cnt;
            }
        }
    }
}
