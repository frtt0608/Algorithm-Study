import java.util.*;

public class 카드짝맞추기_pq {

    class Solution {
        int answer, totalCard, sR, sC;
        int[] dr = {-1,0,1,0}, dc = {0,1,0,-1};
        int[][] board;
        Map<Integer, List<Node>> cards;
        
        class Node implements Comparable<Node> {
            int r, c;
            int moveCnt;
    
            Node(int r, int c, int moveCnt) {
                this.r = r;
                this.c = c;
                this.moveCnt = moveCnt;
            }
    
            @Override
            public int compareTo(Node node) {
                return this.moveCnt - node.moveCnt;
            }
        }
        
        public void getTotalCard() {
            cards = new HashMap<>();
            
            for(int i=0; i<4; i++) {
                for(int j=0; j<4; j++) {
                    if(board[i][j] != 0) {
                        totalCard += 1;
                        
                        List<Node> cardList = new ArrayList<>();
                        if(cards.containsKey(board[i][j])) {
                            cardList = cards.get(board[i][j]);
                        }
                        
                        cardList.add(new Node(i, j, 0));
                        cards.put(board[i][j], cardList);
                    }
                }
            }
        }
        
        public boolean isWall(int x, int y) {
            return x<0 || x>=4 || y<0 || y>=4;
        }
        
        public int searchNumberingCard(int[][] boards,
                                       int tX, int tY, int sr, int sc) {
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(sr, sc, 0));
            int[][] visited = new int[4][4];
            
            for(int i=0; i<4; i++) {
                Arrays.fill(visited[i], Integer.MAX_VALUE);
            }
            
            visited[sr][sc] = 0;
            
            while(!pq.isEmpty()) {
                Node cur = pq.poll();
    
                if(visited[cur.r][cur.c] < cur.moveCnt) continue;
                
                if(cur.r == tX && cur.c == tY)
                    return cur.moveCnt;
                
                for(int dir=0; dir<4; dir++) {
                    int cnt = 0;
                    int nr = cur.r;
                    int nc = cur.c;
                    
                    while(!isWall(nr+dr[dir], nc+dc[dir])) {
                        cnt += 1;
                        nr += dr[dir];
                        nc += dc[dir];
                        
                        if(boards[nr][nc] != 0) break;
                        
                        if(visited[nr][nc] > cur.moveCnt + cnt) {
                            visited[nr][nc] = cur.moveCnt + cnt;
                            pq.offer(new Node(nr, nc, visited[nr][nc]));
                        }
                    }
    
                    if(visited[nr][nc] > cur.moveCnt + 1) {
                        visited[nr][nc] = cur.moveCnt + 1;
                        pq.offer(new Node(nr, nc, visited[nr][nc]));
                    }
                }
            }
            
            return 0;
        }
        
        public void getMinMoveCnt(int[][] boards, int[] order, int r, int c, int idx, int moveCnt) {
            
            if(idx == totalCard/2) {
                answer = Math.min(answer, moveCnt+totalCard);
                return;
            }
            
            Node node1 = cards.get(order[idx]).get(0);
            Node node2 = cards.get(order[idx]).get(1);
            
            int count1 = searchNumberingCard(boards, node1.r, node1.c, r, c) + 
                        searchNumberingCard(boards, node2.r, node2.c, node1.r, node1.c);
            
            int count2 = searchNumberingCard(boards, node2.r, node2.c, r, c) +
                        searchNumberingCard(boards, node1.r, node1.c, node2.r, node2.c);
            
            boards[node1.r][node1.c] = 0;
            boards[node2.r][node2.c] = 0;
            
            // if(count1 > count2) {
            //     getMinMoveCnt(boards, order, node1.r, node1.c, idx+1, moveCnt+count2);    
            // } else {
            //     getMinMoveCnt(boards, order, node2.r, node2.c, idx+1, moveCnt+count1);    
            // }
            
            getMinMoveCnt(boards, order, node1.r, node1.c, idx+1, moveCnt+count2);
            getMinMoveCnt(boards, order, node2.r, node2.c, idx+1, moveCnt+count1);
            boards[node1.r][node1.c] = order[idx];
            boards[node2.r][node2.c] = order[idx];
        }
        
        public void getTotalCase(int[] order, boolean[] visited, int idx) {
            if(idx == totalCard/2) {
                getMinMoveCnt(copyBoard(), order, sR, sC, 0, 0);
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
