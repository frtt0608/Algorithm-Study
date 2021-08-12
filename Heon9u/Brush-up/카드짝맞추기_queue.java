import java.util.*;

public class 카드짝맞추기_queue {
    
    class Solution {
        int answer = 1000;
        int[] dx={-1,0,1,0}, dy={0,1,0,-1};
        List<int[]> orders;
        Map<Integer, List<Node>> cards;
        
        class Node {
            int x, y;
            int moveCnt;
            
            Node(int x, int y, int moveCnt) {
                this.x = x;
                this.y = y;
                this.moveCnt = moveCnt;
            }
        }
        
        public int getNumberingCardCount(int[][] board) {
            int cnt = 0;
            cards = new HashMap<>();
            
            for(int i=0; i<4; i++) {
                for(int j=0; j<4; j++) {
                    if(board[i][j] != 0) {
                        cnt += 1;
                        
                        List<Node> cardList = new ArrayList<>();
                        if(cards.containsKey(board[i][j])) {
                            cardList = cards.get(board[i][j]);
                        }
                        
                        cardList.add(new Node(i, j, 0));
                        cards.put(board[i][j], cardList);
                    }
                }
            }
            
            return cnt/2;
        }
        
        public void getTotalCase(int n, int r, int[] order, boolean[] visited) {
            if(n == r) {
                int[] tempOrder = new int[n];
                System.arraycopy(order, 0, tempOrder, 0, n);
                orders.add(tempOrder);
                return;
            }
            
            for(int i=1; i<n+1; i++) {
                if(visited[i]) continue;
                
                order[r] = i;
                visited[i] = true;
                getTotalCase(n, r+1, order, visited);
                visited[i] = false;
            }
        }
        
        public int[][] copyBoard(int[][] board) {
            int[][] temp = new int[4][4];
            
            for(int i=0; i<4; i++) {
                for(int j=0; j<4; j++) {
                    temp[i][j] = board[i][j];
                }
            }
            return temp;
        }
        
        public void getMinMoveCnt(int[][] board, int x, int y) {
            for(int[] order: orders) {
                matchingTotalCard(copyBoard(board), order, x, y);
            }
        }
        
        public void matchingTotalCard(int[][] board, int[] order, int x, int y) {
            int count=0, count1, count2;
            Node node1, node2;
            
            for(int target: order) {
                node1 = cards.get(target).get(0);
                node2 = cards.get(target).get(1);
                count1 = searchSameCard(board, node1.x, node1.y, x, y) + 
                            searchSameCard(board, node2.x, node2.y, node1.x, node1.y);
                
                count2 = searchSameCard(board, node2.x, node2.y, x, y) + 
                            searchSameCard(board, node1.x, node1.y, node2.x, node2.y);
                
                board[node1.x][node1.y] = 0;
                board[node2.x][node2.y] = 0;
                
                if(count1 < count2) {
                    x = node2.x;
                    y = node2.y;
                    count += count1;
                } else {
                    x = node1.x;
                    y = node1.y;
                    count += count2;
                }
            }
            
            answer = Math.min(answer, count+order.length*2);
        }
        
        public boolean isWall(int x, int y) {
            return x<0 || x>=4 || y<0 || y>=4;
        }
        
        public int searchSameCard(int[][] board, int tX, int tY, int sX, int sY) {
            Queue<Node> que = new LinkedList<>();
            que.offer(new Node(sX, sY, 0));
            boolean[][] visited = new boolean[4][4];
            visited[sX][sY] = true;
            
            while(!que.isEmpty()) {
                Node cur = que.poll();
                
                if(cur.x == tX && cur.y == tY) {
                    // System.out.println("시작점: "+sX+", "+sY);
                    // System.out.println("도착점: "+tX+", "+tY+": "+cur.moveCnt);
                    return cur.moveCnt;
                }
                
                for(int dir=0; dir<4; dir++) {
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];
                    
                    if(isWall(nx, ny) || visited[nx][ny]) continue;
                    
                    visited[nx][ny] = true;
                    que.offer(new Node(nx, ny, cur.moveCnt+1));
                }
                
                for(int dir=0; dir<4; dir++) {
                    int nx = cur.x;
                    int ny = cur.y;
                    
                    while(!isWall(nx+dx[dir], ny+dy[dir])) {
                        nx += dx[dir];
                        ny += dy[dir];
                        
                        if(board[nx][ny] != 0) break;
                    }
                    
                    if(visited[nx][ny]) continue;
                    que.offer(new Node(nx, ny, cur.moveCnt+1));
                }
            }
            
            return 0;
        }
        
        public int solution(int[][] board, int r, int c) {
            orders = new ArrayList<>();
            int card = getNumberingCardCount(board);
            
            getTotalCase(card, 0, new int[card], new boolean[card+1]);
            getMinMoveCnt(board, r, c);
            
            return answer;
        }
    }
}
