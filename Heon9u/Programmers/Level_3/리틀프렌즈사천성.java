import java.util.*;

// DFS와 BFS
// 정답은 알파벳 순, DFS로 순회하면서 방문체크를 초기화할 필요X
public class 리틀프렌즈사천성 {

    class Solution {
        String answer;
        int m, n;
        int[] dx={-1,0,1,0}, dy={0,1,0,-1};
        List<Character> tiles = new ArrayList<>();
        Map<Character, List<Node>> mapOfTiles = new HashMap<>();
        boolean isMatching;
        
        public char[][] setBoards(String[] board) {
            char[][] boards = new char[m][n];
            
            for(int i=0; i<m; i++) {
                for(int j=0; j<n; j++) {
                    boards[i][j] = board[i].charAt(j);
                    if(boards[i][j] >= 'A' && boards[i][j] <= 'Z') {
                        
                        List<Node> tileList = new ArrayList<>();
                        if(mapOfTiles.containsKey(boards[i][j]))
                            tileList = mapOfTiles.get(boards[i][j]);
                        else
                            tiles.add(boards[i][j]);
                        
                        tileList.add(new Node(boards[i][j], i, j));
                        mapOfTiles.put(boards[i][j], tileList);
                    }
                }
            }
            
            return boards;
        }
        
        public void matchingCardInBoard(char[][] boards, boolean[] visited,
                                        String removeOrder) {
            if(isMatching) return;
            
            if(removeOrder.length() == tiles.size()) {
                answer = removeOrder;
                isMatching = true;
                return;
            }
            
            for(int i=0; i<tiles.size(); i++) {
                if(visited[i]) continue;
                if(isMatching) return;
                
                char target = tiles.get(i);
                Node start = mapOfTiles.get(target).get(0);
                Node end = mapOfTiles.get(target).get(1);
                
                if(searchSameTile(boards, end, start.x, start.y)) {
                    boards[start.x][start.y] = '.';
                    boards[end.x][end.y] = '.';
                    visited[i] = true;
                    
                    matchingCardInBoard(boards, visited, removeOrder+target);
                }
            }
        }
        
        public boolean searchSameTile(char[][] boards, Node target, int sX, int sY) {
            Queue<Node> que = new LinkedList<>();
            que.offer(new Node(sX, sY, -1, 0));
            
            while(!que.isEmpty()) {
                Node cur = que.poll();
                
                for(int dir=0; dir<4; dir++) {
                    if(cur.dir != -1 && Math.abs(cur.dir-dir) == 2) continue;
                    
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];
                    
                    if(isWall(nx, ny)) continue;
                    int nextCnt = (cur.dir == -1 || cur.dir == dir) ? cur.rotateCnt:cur.rotateCnt+1;
                    if(nextCnt <= 1) {
                        if(boards[nx][ny] == '.') {
                            que.offer(new Node(nx, ny, dir, nextCnt));
                        } else if(nx == target.x && ny == target.y) {
                            return true;
                        }
                    }
                }
            }
            
            return false;
        }
        
        public boolean isWall(int x, int y) {
            return x<0 || x>=m || y<0 || y>=n;
        }
        
        public String solution(int m, int n, String[] board) {
            answer = "IMPOSSIBLE";
            this.m = m;
            this.n = n;
            
            char[][] boards = setBoards(board);
            Collections.sort(tiles);
            matchingCardInBoard(boards, new boolean[tiles.size()], "");
            
            return answer;
        }
        
        class Node {
            char tile;
            int x, y, dir;
            int rotateCnt;
            
            Node(char tile, int x, int y) {
                this.tile = tile;
                this.x = x;
                this.y = y;
            }
            
            Node(int x, int y, int dir, int rotateCnt) {
                this.x = x;
                this.y = y;
                this.dir = dir;
                this.rotateCnt = rotateCnt;
            }
        }
    }
}
