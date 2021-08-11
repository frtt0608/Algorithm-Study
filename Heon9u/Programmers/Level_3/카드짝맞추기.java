import java.util.*;

public class 카드짝맞추기 {

    class Solution {
        int answer, totalCard, sR, sC;
        int[] dr = {-1,0,1,0}, dc = {0,1,0,-1};
        int[][] board;
        
        class Node {
            int r, c;
            int moveCnt;
            
            Node(int r, int c, int moveCnt) {
                this.r = r;
                this.c = c;
                this.moveCnt = moveCnt;
            }
        }
        
        public void getTotalCard() {
            for(int i=0; i<4; i++) {
                for(int j=0; j<4; j++) {
                    if(board[i][j] != 0) {
                        totalCard += 1;
                    }
                }
            }
        }
        
        public boolean isWall(int x, int y) {
            return x<0 || x>=4 || y<0 || y>=4;
        }
        
        public int searchNumberingCard(int[][] board,
                                       int target, Node node) {
            Queue<Node> pq = new LinkedList<>();
            pq.offer(new Node(node.r, node.c, 0));
            boolean[][] visited = new boolean[4][4];
            visited[node.r][node.c] = true;
            
            while(!pq.isEmpty()) {
                Node cur = pq.poll();
    
                if(board[cur.r][cur.c] == target) {
                    node.r = cur.r;
                    node.c = cur.c;
                    board[node.r][node.c] = 0;
                    return cur.moveCnt;
                }
                
                for(int dir=0; dir<4; dir++) {
                    int nr = cur.r + dr[dir];
                    int nc = cur.c + dc[dir];
                    
                    if(isWall(nr, nc) || visited[nr][nc]) continue;
                    
                    visited[nr][nc] = true;
                    pq.offer(new Node(nr, nc, cur.moveCnt+1));
                }
                
                for(int dir=0; dir<4; dir++) {
                    Node ctrl = checkCtrlRoute(board, dir, cur.r, cur.c);
                    int nr = ctrl.r;
                    int nc = ctrl.c;
                    
                    if(visited[nr][nc]) continue;
                    visited[nr][nc] = true;
                    
                    pq.offer(new Node(nr, nc, cur.moveCnt+1));
                }
            }
            
            return 0;
        }
        
        public Node checkCtrlRoute(int[][] board, int dir, int r, int c) {
            
            r += dr[dir];
            c += dc[dir];
            
            while(!isWall(r, c)) {
                if(board[r][c] != 0) return new Node(r, c, 0);
                
                r += dr[dir];
                c += dc[dir];
            }
            
            return new Node(r-dr[dir], c-dc[dir], 0);
        }
        
        public void getMinMoveCnt(int[][] boards, int[] order) {
            Node node = new Node(sR, sC, 0);
            
            for(int target: order) {
                node.moveCnt += searchNumberingCard(boards, target, node);
                node.moveCnt += searchNumberingCard(boards, target, node);
            }
            
            answer = Math.min(answer, node.moveCnt + totalCard);
        }
        
        public void getTotalCase(int[] order, boolean[] visited, int idx) {
            if(idx == totalCard/2) {
                getMinMoveCnt(copyBoard(), order);
                return;
            }
            
            for(int i=1; i<totalCard/2+1; i++) {
                if(visited[i]) continue;
                order[idx] = i;
                visited[i] = true;
                getTotalCase(order, visited, idx+1);
                order[idx] = 0;
                visited[i] = false;
            }
        }
        
        public int[][] copyBoard() {
            int[][] temp = new int[4][4];
            
            for(int i=0; i<4; i++) {
                for(int j=0; j<4; j++) {
                    temp[i][j] = board[i][j];
                }
            }
            
            return temp;
        }
        
        public int solution(int[][] board, int r, int c) {
            answer = Integer.MAX_VALUE;
            this.sR = r;
            this.sC = c;
            this.board = board;
            getTotalCard();
            getTotalCase(new int[totalCard/2], new boolean[totalCard/2+1], 0);
            
            return answer;
        }
    }
}
